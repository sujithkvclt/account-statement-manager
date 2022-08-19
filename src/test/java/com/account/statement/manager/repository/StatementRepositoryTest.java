package com.account.statement.manager.repository;

import com.account.statement.manager.entity.Statement;
import com.account.statement.manager.repository.mapper.StatementRowMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class StatementRepositoryTest {
    @Autowired
    StatementRepository statementRepository;
    @MockBean
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("Test getAccountByAccountId")
    void getAccountByAccountIdTestSuccess() {
        when(jdbcTemplate.query(eq("SELECT * FROM statement WHERE account_id = ?"), Mockito.any(StatementRowMapper.class), eq(1L))).thenReturn(getStatementsByAccountId());

        List<Statement> statements = statementRepository.getStatementsByAccountId(1L);

        Assertions.assertEquals(1L, statements.get(0).getId(), "Value of id should be '1'.");
        Assertions.assertEquals(1L, statements.get(0).getAccountId(), "Value of accountId should be '1'.");
        Assertions.assertEquals(Double.valueOf(100.000), statements.get(0)
                .getAmount(), "Value of amount should be " + "'100.000'");
    }

    @Test
    @DisplayName("Test getStatements")
    void getAccountsTestSuccess() {
        when(jdbcTemplate.query(eq("SELECT * FROM statement"), Mockito.any(StatementRowMapper.class))).thenReturn(getStatements());

        List<Statement> statements = statementRepository.getStatements();

        Assertions.assertEquals(10, statements.size(), "getStatements should return 10 statements");
    }

    private List<Statement> getStatements() {
        Statement statement1 = new Statement(1L, 1L, LocalDate.of(2022, 06, 01), Double.valueOf(100.000));
        Statement statement2 = new Statement(2L, 1L, LocalDate.of(2021, 05, 11), Double.valueOf(90.3245));
        Statement statement3 = new Statement(3L, 3L, LocalDate.of(2021, 06, 11), Double.valueOf(1000.54325));
        Statement statement4 = new Statement(4L, 4L, LocalDate.of(2020, 03, 18), Double.valueOf(75.123));
        Statement statement5 = new Statement(5L, 3L, LocalDate.of(2022, 03, 22), Double.valueOf(1020.12));
        Statement statement6 = new Statement(6L, 2L, LocalDate.of(2022, 04, 30), Double.valueOf(102));
        Statement statement7 = new Statement(7L, 4L, LocalDate.of(2018, 10, 07), Double.valueOf(01));
        Statement statement8 = new Statement(8L, 1L, LocalDate.of(2016, 02, 04), Double.valueOf(87));
        Statement statement9 = new Statement(9L, 5L, LocalDate.of(2021, 01, 02), Double.valueOf(100));
        Statement statement10 = new Statement(10L, 3L, LocalDate.of(2020, 06, 01), Double.valueOf(110));

        return Arrays.asList(statement1, statement2, statement3, statement4, statement5, statement6, statement7, statement8, statement9, statement10);
    }

    private List<Statement> getStatementsByAccountId() {
        Statement statement1 = new Statement(1L, 1L, LocalDate.of(2022, 06, 01), Double.valueOf(100.000));
        Statement statement2 = new Statement(2L, 1L, LocalDate.of(2021, 05, 11), Double.valueOf(90.3245));
        Statement statement3 = new Statement(3L, 1L, LocalDate.of(2021, 06, 11), Double.valueOf(1000.54325));
        Statement statement4 = new Statement(4L, 1L, LocalDate.of(2020, 03, 18), Double.valueOf(75.123));

        return Arrays.asList(statement1, statement2, statement3, statement4);
    }
}