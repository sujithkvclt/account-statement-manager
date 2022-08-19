package com.account.statement.manager.aop;

import com.account.statement.manager.constant.ApplicationConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.UUID;

/**
 * The interceptor class in implemented to acquire the global logging functionality.
 *
 * @author Sujith K V
 */
@Slf4j
@Aspect
@Component
public class LoggingAspect {

    ObjectMapper mapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

    /**
     * The method is implemented to log all the <code>HTTP</code> request and responses. It will also log the elapsed
     * time as well.
     *
     * @param joinPoint
     * @return response <code>Object</code>
     */
    @Around("execution(* com.account.statement.manager.controller..*(..)))")
    public Object logApiRequestAndResponse(final ProceedingJoinPoint joinPoint) throws Throwable {
        MDC.clear();
        MDC.put(ApplicationConstants.MDC_SESSION_ID, UUID.randomUUID().toString());
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        StringBuilder builder = new StringBuilder();

        builder = builder.append("Invoked ").append(methodSignature.getDeclaringType().getSimpleName()).append(".")
                .append(methodSignature.getName()).append(" with arguments : ")
                .append(Arrays.toString(joinPoint.getArgs()));
        log.info(builder.toString());
        builder.setLength(0);

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();

        builder = builder.append("Executed ").append(methodSignature.getDeclaringType().getSimpleName()).append(".")
                .append(methodSignature.getName()).append(" in ").append(stopWatch.getTotalTimeMillis())
                .append(" milliseconds with Response JSON : ").append(mapper.writeValueAsString(result));
        log.info(builder.toString());

        return result;
    }

    /**
     * The method is implemented to log all the <code>HTTP</code> error responses.4
     *
     * @param joinPoint
     * @param result    response <code>Object</code>
     */
    @AfterReturning(pointcut = "execution(* com.account.statement.manager.exception.controller..*(..)))", returning = "result")
    public void logErrorResponse(JoinPoint joinPoint, Object result) throws JsonProcessingException {
        StringBuilder builder = new StringBuilder().append("Execution failed with Response JSON : ")
                .append(mapper.writeValueAsString(result));
        log.info(builder.toString());
    }
}