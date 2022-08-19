package com.account.statement.manager.util;

import com.account.statement.manager.constant.ApplicationConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class DateUtilTest {

    @Test
    @DisplayName("stringToLocalDate success Test with MMDDYYYY format")
    void stringToLocalDateSuccessMMDDYYYYTest() {

        Assertions.assertEquals(LocalDate.of(2022, 5, 30),
                DateUtil.stringToLocalDate("05302022", ApplicationConstants.DATE_FORMAT_MMDDYYYY));
    }

    @Test
    @DisplayName("stringToLocalDate success Test with DD.MM.YYYY format")
    void stringToLocalDateSuccessDDMMYYYYTest() {

        Assertions.assertEquals(LocalDate.of(2022, 5, 30),
                DateUtil.stringToLocalDate("30.05.2022", ApplicationConstants.DATE_FORMAT_DDMMYYYY));
    }

    @Test
    @DisplayName("stringToLocalDate failure Test with non date input")
    void stringToLocalDateFailureTest() {

        Assertions.assertNull(DateUtil.stringToLocalDate("abcd", ApplicationConstants.DATE_FORMAT_DDMMYYYY));
    }

    @Test
    @DisplayName("stringToLocalDate failure Test with invalid date format input")
    void stringToLocalDateInvalidDateFailureTest() {

        Assertions.assertNull(DateUtil.stringToLocalDate("10302020", ApplicationConstants.DATE_FORMAT_DDMMYYYY));
    }
}