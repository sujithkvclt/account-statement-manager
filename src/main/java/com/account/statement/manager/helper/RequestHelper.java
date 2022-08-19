package com.account.statement.manager.helper;

import com.account.statement.manager.constant.ApplicationConstants;
import com.account.statement.manager.dto.StatementRequest;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Objects;

import static com.account.statement.manager.util.DateUtil.stringToLocalDate;

/**
 * Helper class to prepare service request objects based on the HTTP request parameters.
 *
 * @author Sujith k V
 */
@Slf4j
public class RequestHelper {
    private RequestHelper() {
    }

    /**
     * Method to create statementRequest object with default values.
     */
    public static StatementRequest creteStatementRequest(String accountId) {
        return StatementRequest.builder().accountId(Long.valueOf(accountId)).fromDate(LocalDate.now().minusMonths(3))
                .toDate(LocalDate.now()).fromAmount(Double.MIN_VALUE).toAmount(Double.MAX_VALUE).build();
    }

    /**
     * Method to create statementRequest based on the API request parameter. if the value of request paramenter is
     * null, method will set the default value.
     */
    public static StatementRequest creteStatementRequest(String accountId, String fromDate, String toDate,
                                                         String fromAmount, String toAmount) {
        return StatementRequest.builder().accountId(Long.valueOf(accountId))
                .fromDate(Objects.nonNull(fromDate) ? stringToLocalDate(fromDate, ApplicationConstants.DATE_FORMAT_MMDDYYYY) : LocalDate.now()
                        .minusMonths(3))
                .toDate(Objects.nonNull(toDate) ? stringToLocalDate(toDate, ApplicationConstants.DATE_FORMAT_MMDDYYYY) : LocalDate.now())
                .fromAmount(Objects.nonNull(fromAmount) ? Double.parseDouble(fromAmount) : Double.MIN_VALUE)
                .toAmount(Objects.nonNull(toAmount) ? Double.parseDouble(toAmount) : Double.MAX_VALUE).build();
    }
}