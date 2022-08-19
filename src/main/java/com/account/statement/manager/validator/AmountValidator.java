package com.account.statement.manager.validator;

import com.account.statement.manager.annotation.Amount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * Validator class for amount validation
 *
 * @author Sujith K V
 */
public class AmountValidator implements ConstraintValidator<Amount, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            if (Objects.nonNull(value)) {
                Double.parseDouble(value);
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}