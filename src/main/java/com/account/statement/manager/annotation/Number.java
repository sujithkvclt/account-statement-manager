package com.account.statement.manager.annotation;

import com.account.statement.manager.validator.NumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * The annotated element must be a decimal number.
 * <p>
 * Supported types are:
 * <ul>
 *     <li>{@code String}</li
 * </ul>
 * {@code null} elements are considered valid.
 *
 * @author Sujith K V
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Constraint(validatedBy = NumberValidator.class)
public @interface Number {
    String message() default "{validation.constraints.number.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
