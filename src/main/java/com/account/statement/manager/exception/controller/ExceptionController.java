package com.account.statement.manager.exception.controller;

import com.account.statement.manager.dto.error.ApiError;
import com.account.statement.manager.dto.error.ApiResponse;
import com.account.statement.manager.dto.error.ErrorDetail;
import com.account.statement.manager.enums.ErrorEnum;
import com.account.statement.manager.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Global error handling class.
 *
 * @author Sujith k V
 */
@ControllerAdvice
public class ExceptionController {

    /**
     * Handle general exceptions.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> exceptionHandler(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ApiResponse<>(new ApiError(ErrorEnum.APP500001)), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle {@link ConstraintViolationException} errors.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex) {
        List<ErrorDetail> details = new ArrayList<>();

        ex.getConstraintViolations().forEach(violation -> details.add(ErrorDetail.builder()
                .field(violation.getPropertyPath().toString().split(Pattern.quote("."))[1])
                .message(violation.getMessage()).build()));

        return new ResponseEntity<>(new ApiResponse<>(new ApiError(ErrorEnum.APP400001, details)), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle {@link ResourceNotFoundException} errors.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiResponse<Object>> resourceNotFoundException(ResourceNotFoundException ex,
                                                                         WebRequest request) {
        return new ResponseEntity<>(new ApiResponse<>(ex.getApiError()), HttpStatus.NOT_FOUND);
    }
}
