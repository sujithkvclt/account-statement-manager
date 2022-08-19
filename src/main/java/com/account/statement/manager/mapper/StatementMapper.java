package com.account.statement.manager.mapper;

import com.account.statement.manager.dto.StatementResponse;
import com.account.statement.manager.entity.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class for mapping Statement class to StatementResponse
 *
 * @author Sujith K V
 */
public class StatementMapper {
    private StatementMapper() {
    }

    /**
     * Method to map list of Statement to list of StatementResponse
     *
     * @param statements
     * @return list of StatementResponse
     */
    public static List<StatementResponse> map(List<Statement> statements) {
        if (statements == null) {
            return new ArrayList<>();
        }

        List<StatementResponse> list = new ArrayList<>(statements.size());

        for (Statement statement : statements) {
            list.add(map(statement));
        }

        return list;
    }

    /**
     * Method to map Statement class to StatementResponse
     *
     * @param statement
     * @return StatementResponse
     */
    public static StatementResponse map(Statement statement) {
        if (statement == null) {
            return null;
        }

        return StatementResponse.builder().id(statement.getId()).dateField(statement.getDateField())
                .amount(statement.getAmount()).build();
    }
}