package com.account.statement.manager.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for Date.
 *
 * @author Sujith K V
 */
@Slf4j
@Component
public class DateUtil {
    private DateUtil() {
    }

    /**
     * Method to convert date string into LocalDate based on the input pattern
     *
     * @param dateString must not be null
     * @param pattern    must not be null
     * @return LocalDate
     */
    public static LocalDate stringToLocalDate(String dateString, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            log.info("date parsing failed {}", e);
        }

        return null;
    }
}