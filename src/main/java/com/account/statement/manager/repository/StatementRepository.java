package com.account.statement.manager.repository;

import com.account.statement.manager.entity.Statement;

import java.util.List;

/**
 * Repository interface for Statement table
 *
 * @author Sujith K V
 */
public interface StatementRepository {
    List<Statement> getStatements();

    List<Statement> getStatementsByAccountId(final Long accountId);
}