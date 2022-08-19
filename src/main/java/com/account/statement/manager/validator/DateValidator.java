package com.account.statement.manager.validator;

import com.account.statement.manager.annotation.Date;
import com.account.statement.manager.constant.ApplicationConstants;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Objects;
/**
 * Validator class for date validation
 *
 * @author Sujith K V
 */
public class DateValidator implements ConstraintValidator<Date, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ApplicationConstants.DATE_FORMAT_MMDDYYYY, Locale.ENGLISH);

        try {
            if (Objects.nonNull(value)) {
                LocalDate.parse(value, formatter);
            }
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}