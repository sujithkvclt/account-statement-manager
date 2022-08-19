package com.account.statement.manager.controller;

import com.account.statement.manager.dto.AccountStatementResponse;
import com.account.statement.manager.dto.StatementRequest;
import com.account.statement.manager.dto.StatementResponse;
import com.account.statement.manager.dto.error.ApiResponse;
import com.account.statement.manager.entity.Statement;
import com.account.statement.manager.mapper.StatementMapper;
import com.account.statement.manager.service.StatementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class StatementControllerTest {
    @Autowired
    StatementController statementController;
    @MockBean
    StatementService statementService;

    private List<StatementResponse> getStatements() {
        Statement statement1 = new Statement(1L, 1L, LocalDate.of(2022, 06, 01), Double.valueOf(100.000));
        Statement statement2 = new Statement(2L, 1L, LocalDate.of(2021, 05, 11), Double.valueOf(90.3245));
        Statement statement3 = new Statement(3L, 1L, LocalDate.of(2021, 06, 11), Double.valueOf(1000.54325));
        Statement statement4 = new Statement(4L, 1L, LocalDate.of(2020, 03, 18), Double.valueOf(75.123));

        return StatementMapper.map(Arrays.asList(statement1, statement2, statement3, statement4));
    }

    @Test
    @DisplayName("getStatements test")
    void getStatementsTest() {
        AccountStatementResponse statements = AccountStatementResponse.builder().accountId(1L).accountNumber("123456")
                .statements(getStatements()).build();
        StatementRequest request = StatementRequest.builder().accountId(Long.valueOf(1L))
                .fromDate(LocalDate.now().minusMonths(3)).toDate(LocalDate.now()).fromAmount(Double.MIN_VALUE)
                .toAmount(Double.MAX_VALUE).build();

        when(statementService.getStatements(request)).thenReturn(statements);

        ResponseEntity<ApiResponse<AccountStatementResponse>> statementsResponse = statementController.getStatements("1", null, null, null, null);

        Assertions.assertEquals(1L, statementsResponse.getBody().getData().getAccountId());
        Assertions.assertEquals("123456", statementsResponse.getBody().getData().getAccountNumber());
        Assertions.assertEquals(4L, statementsResponse.getBody().getData().getStatements().size());
    }

    ;

    @Test
    @DisplayName("getUserStatements test")
    void getUserStatementsTest() {
        AccountStatementResponse statements = AccountStatementResponse.builder().accountId(1L).accountNumber("123456")
                .statements(getStatements()).build();
        StatementRequest request = StatementRequest.builder().accountId(Long.valueOf(1L))
                .fromDate(LocalDate.now().minusMonths(3)).toDate(LocalDate.now()).fromAmount(Double.MIN_VALUE)
                .toAmount(Double.MAX_VALUE).build();

        when(statementService.getStatements(request)).thenReturn(statements);

        ResponseEntity<ApiResponse<AccountStatementResponse>> statementsResponse = statementController.getUserStatements("1");

        Assertions.assertEquals(1L, statementsResponse.getBody().getData().getAccountId());
        Assertions.assertEquals("123456", statementsResponse.getBody().getData().getAccountNumber());
        Assertions.assertEquals(4L, statementsResponse.getBody().getData().getStatements().size());
    }


}