package com.account.statement.manager.service;

import com.account.statement.manager.dto.AccountStatementResponse;
import com.account.statement.manager.dto.StatementRequest;

/**
 * Service interface for statement related operations.
 *
 * @author Sujith K V
 */
public interface StatementService {
    public AccountStatementResponse getStatements(final StatementRequest request);
}
