package com.account.statement.manager.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DateValidatorTest {

    @Test
    @DisplayName("isValid success test")
    void isValidTest() {

        DateValidator dateValidator = new DateValidator();
        Assertions.assertTrue(dateValidator.isValid("05301991", null));
    }

    @Test
    @DisplayName("isValid invalid date format test")
    void isValidInvalidDateTest() {

        DateValidator dateValidator = new DateValidator();
        Assertions.assertFalse(dateValidator.isValid("20051991", null));
    }

    @Test
    @DisplayName("isValid Null date format test")
    void isValidNullDateTest() {

        DateValidator dateValidator = new DateValidator();
        Assertions.assertTrue(dateValidator.isValid(null, null));
    }

    @Test
    @DisplayName("isValid Null date format test")
    void isValidNonDateStringDateTest() {

        DateValidator dateValidator = new DateValidator();
        Assertions.assertFalse(dateValidator.isValid("abcd", null));
    }
}