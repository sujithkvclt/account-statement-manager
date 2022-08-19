package com.account.statement.manager.annotation;

import com.account.statement.manager.validator.DateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * The annotated element must be a date in the <code>MMDDYYYY</code> format.
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
@Constraint(validatedBy = DateValidator.class)
public @interface Date {
    String message() default "{validation.constraints.date.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
