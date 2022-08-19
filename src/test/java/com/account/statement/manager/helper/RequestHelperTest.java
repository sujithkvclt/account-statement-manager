package com.account.statement.manager.helper;

import com.account.statement.manager.constant.ApplicationConstants;
import com.account.statement.manager.dto.StatementRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Objects;

import static com.account.statement.manager.util.DateUtil.stringToLocalDate;

@SpringBootTest
class RequestHelperTest {
    public static StatementRequest creteStatementRequest(String accountId) {
        return StatementRequest.builder().accountId(Long.valueOf(accountId)).fromDate(LocalDate.now().minusMonths(3))
                .toDate(LocalDate.now()).fromAmount(Double.MIN_VALUE).toAmount(Double.MAX_VALUE).build();
    }

    public static StatementRequest creteStatementRequest(String accountId, String fromDate, String toDate,
                                                         String fromAmount, String toAmount) {
        return StatementRequest.builder().accountId(Long.valueOf(accountId))
                .fromDate(Objects.nonNull(fromDate) ? stringToLocalDate(fromDate, ApplicationConstants.DATE_FORMAT_MMDDYYYY) : LocalDate.now()
                        .minusMonths(3))
                .toDate(Objects.nonNull(toDate) ? stringToLocalDate(toDate, ApplicationConstants.DATE_FORMAT_MMDDYYYY) : LocalDate.now())
                .fromAmount(Objects.nonNull(fromAmount) ? Double.valueOf(fromAmount) : Double.MIN_VALUE)
                .toAmount(Objects.nonNull(toAmount) ? Double.valueOf(toAmount) : Double.MAX_VALUE).build();
    }

    @Test
    @DisplayName("creteStatementRequest with account id and all the parameter as null Test")
    void creteStatementRequestAllParameterTest() {
        StatementRequest request = RequestHelper.creteStatementRequest("1", null, null, null, null);

        Assertions.assertEquals(1L, request.getAccountId());
        Assertions.assertEquals(LocalDate.now().minusMonths(3), request.getFromDate());
        Assertions.assertEquals(LocalDate.now(), request.getToDate());
        Assertions.assertEquals(Double.MIN_VALUE, request.getFromAmount());
        Assertions.assertEquals(Double.MAX_VALUE, request.getToAmount());
    }

    @Test
    @DisplayName("creteStatementRequest with all the parameter Test")
    void creteStatementRequestAllParameterWithValueTest() {
        StatementRequest request = RequestHelper.creteStatementRequest("1", "07102022", "07152022", "50", "100.56");

        Assertions.assertEquals(1L, request.getAccountId());
        Assertions.assertEquals(LocalDate.of(2022, 07, 10), request.getFromDate());
        Assertions.assertEquals(LocalDate.of(2022, 07, 15), request.getToDate());
        Assertions.assertEquals(Double.valueOf(50.0), request.getFromAmount());
        Assertions.assertEquals(Double.valueOf(100.56), request.getToAmount());
    }

    @Test
    @DisplayName("creteStatementRequest with only accountId Test")
    void creteStatementRequestTest() {
        StatementRequest request = RequestHelper.creteStatementRequest("1");

        Assertions.assertEquals(1L, request.getAccountId());
        Assertions.assertEquals(LocalDate.now().minusMonths(3), request.getFromDate());
        Assertions.assertEquals(LocalDate.now(), request.getToDate());
        Assertions.assertEquals(Double.MIN_VALUE, request.getFromAmount());
        Assertions.assertEquals(Double.MAX_VALUE, request.getToAmount());
    }
}