package com.account.statement.manager.annotation;

import com.account.statement.manager.validator.AmountValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * The annotated element must be a number in the currency format.
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
@Constraint(validatedBy = AmountValidator.class)
public @interface Amount {
    String message() default "{validation.constraints.amount.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
