package com.account.statement.manager.validator;

import com.account.statement.manager.annotation.Number;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * Validator class for number validation
 *
 * @author Sujith K V
 */
public class NumberValidator implements ConstraintValidator<Number, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            if (Objects.nonNull(value)) {
                Long.parseLong(value);
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}