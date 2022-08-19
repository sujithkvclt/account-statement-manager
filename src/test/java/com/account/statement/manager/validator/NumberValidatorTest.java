package com.account.statement.manager.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumberValidatorTest{

    @Test
    @DisplayName("isValid success test")
    void isValidTest() {

        NumberValidator numberValidator = new NumberValidator();
        Assertions.assertTrue(numberValidator.isValid("1", null));
    }

    @Test
    @DisplayName("isValid null test")
    void isValidNullTest() {

        NumberValidator numberValidator = new NumberValidator();
        Assertions.assertTrue(numberValidator.isValid(null, null));
    }

    @Test
    @DisplayName("isValid test with a non number characters")
    void isValidNonNumberTest() {

        NumberValidator numberValidator = new NumberValidator();
        Assertions.assertFalse(numberValidator.isValid("ab", null));
    }

    @Test
    @DisplayName("isValid test success with double value")
    void isValidDoubleValueTest() {

        NumberValidator numberValidator = new NumberValidator();
        Assertions.assertFalse(numberValidator.isValid("1.00", null));
    }
}