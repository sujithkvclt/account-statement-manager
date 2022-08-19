package com.account.statement.manager.service;

import com.account.statement.manager.dto.AccountStatementResponse;
import com.account.statement.manager.dto.StatementRequest;
import com.account.statement.manager.entity.Account;
import com.account.statement.manager.entity.Statement;
import com.account.statement.manager.exception.ResourceNotFoundException;
import com.account.statement.manager.repository.AccountRepository;
import com.account.statement.manager.repository.StatementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
class StatementServiceTest {
    @Autowired
    StatementService statementService;
    @MockBean
    StatementRepository statementRepository;
    @MockBean
    AccountRepository accountRepository;

    private List<Statement> getStatements() {
        Statement statement1 = new Statement(1L, 1L, LocalDate.now().minusMonths(2)
                .minusDays(10), Double.valueOf(100.000));
        Statement statement2 = new Statement(2L, 1L, LocalDate.of(2021, 05, 11), Double.valueOf(90.3245));
        Statement statement3 = new Statement(3L, 3L, LocalDate.of(2021, 06, 11), Double.valueOf(1000.54325));
        Statement statement4 = new Statement(4L, 4L, LocalDate.of(2020, 03, 18), Double.valueOf(75.123));
        Statement statement5 = new Statement(5L, 3L, LocalDate.of(2022, 03, 22), Double.valueOf(1020.12));
        Statement statement6 = new Statement(6L, 2L, LocalDate.now().minusMonths(1).minusDays(10), Double.valueOf(102));
        Statement statement7 = new Statement(7L, 4L, LocalDate.of(2018, 10, 07), Double.valueOf(01));
        Statement statement8 = new Statement(8L, 1L, LocalDate.of(2016, 02, 04), Double.valueOf(87));
        Statement statement9 = new Statement(9L, 5L, LocalDate.of(2021, 01, 02), Double.valueOf(100));
        Statement statement10 = new Statement(10L, 3L, LocalDate.of(2020, 06, 01), Double.valueOf(110));

        return Arrays.asList(statement1, statement2, statement3, statement4, statement5, statement6, statement7, statement8, statement9, statement10);
    }

    @Test
    @DisplayName("getStatements test with default input values")
    void getStatementsTestWithDefaultInputValues() {
        Account mockAccount = new Account(1L, "current", "0012250016001");

        when(accountRepository.getAccountByAccountId(1L)).thenReturn(Optional.of(mockAccount));
        when(statementRepository.getStatementsByAccountId(1L)).thenReturn(getStatements());

        StatementRequest request = StatementRequest.builder().accountId(Long.valueOf(1L))
                .fromDate(LocalDate.now().minusMonths(3)).toDate(LocalDate.now()).fromAmount(Double.MIN_VALUE)
                .toAmount(Double.MAX_VALUE).build();
        AccountStatementResponse statements = statementService.getStatements(request);

        Assertions.assertEquals(2, statements.getStatements().size());
        Assertions.assertEquals(1L, statements.getAccountId());
        statements.getStatements().forEach(statementResponse -> {
            Assertions.assertTrue(statementResponse.getDateField().isAfter(LocalDate.now().minusMonths(3)));
            Assertions.assertTrue(statementResponse.getDateField().isBefore(LocalDate.now()));
        });
    }

    @Test
    @DisplayName("getStatements test with invalid account id")
    void getStatementsTestWithInvalidAccountId() {
        when(accountRepository.getAccountByAccountId(1L)).thenReturn(Optional.empty());
        when(statementRepository.getStatementsByAccountId(1L)).thenReturn(getStatements());

        StatementRequest request = StatementRequest.builder().accountId(Long.valueOf(1L))
                .fromDate(LocalDate.now().minusMonths(3)).toDate(LocalDate.now()).fromAmount(Double.MIN_VALUE)
                .toAmount(Double.MAX_VALUE).build();
        ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> statementService.getStatements(request));

        Assertions.assertEquals("Account information not found.", exception.getApiError().getMessage());
        Assertions.assertEquals("404-001", exception.getApiError().getCode());
    }
}