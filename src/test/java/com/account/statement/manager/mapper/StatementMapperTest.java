package com.account.statement.manager.mapper;

import com.account.statement.manager.dto.StatementResponse;
import com.account.statement.manager.entity.Statement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class StatementMapperTest {

    @Test
    @DisplayName("statement to statement response map Test")
    void mapStatementNullTest() {
        Assertions.assertNull(StatementMapper.map((Statement) null));
    }

    @Test
    @DisplayName("map list of statements Test")
    void mapListOfStatementNullTest() {
        Assertions.assertEquals(new ArrayList<>(), StatementMapper.map((List<Statement>) null));
    }

    @Test
    @DisplayName("map valid statement Test")
    void mapValidStatementTest() {
        Assertions.assertEquals(new StatementResponse(1L, LocalDate.of(2022, 1, 1), 100.0), StatementMapper.map(new Statement(1L, 1L, LocalDate.of(2022, 1, 1), 100.0)));
    }

    @Test
    @DisplayName("map valid list of statement Test")
    void mapValidListOfStatementTest() {
        Assertions.assertEquals(getStatementsResponses(), StatementMapper.map(getStatements()));
    }

    private List<Statement> getStatements() {
        Statement statement1 = new Statement(1L, 1L, LocalDate.now().minusMonths(2)
                .minusDays(10), Double.valueOf(100.000));
        Statement statement2 = new Statement(2L, 1L, LocalDate.of(2021, 05, 11), Double.valueOf(90.3245));
        Statement statement3 = new Statement(3L, 3L, LocalDate.of(2021, 06, 11), Double.valueOf(1000.54325));
        return Arrays.asList(statement1, statement2, statement3);
    }

    private List<StatementResponse> getStatementsResponses() {
        StatementResponse statement1 = new StatementResponse(1L, LocalDate.now().minusMonths(2)
                .minusDays(10), Double.valueOf(100.000));
        StatementResponse statement2 = new StatementResponse(2L, LocalDate.of(2021, 05, 11), Double.valueOf(90.3245));
        StatementResponse statement3 = new StatementResponse(3L, LocalDate.of(2021, 06, 11), Double.valueOf(1000.54325));
        return Arrays.asList(statement1, statement2, statement3);
    }
}