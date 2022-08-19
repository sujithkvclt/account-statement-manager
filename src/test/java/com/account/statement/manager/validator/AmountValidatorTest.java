package com.account.statement.manager.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AmountValidatorTest {

    @DisplayName("isValid success test")
    @ParameterizedTest
    @ValueSource(strings = {"1","10.00"})
    @NullSource
    void isValidTest(String input) {

        AmountValidator amountValidator = new AmountValidator();
        Assertions.assertTrue(amountValidator.isValid(input, null));
    }

    @Test
    @DisplayName("isValid test with a non number characters")
    void isValidNonNumberTest() {

        AmountValidator amountValidator = new AmountValidator();
        Assertions.assertFalse(amountValidator.isValid("ab", null));
    }
}