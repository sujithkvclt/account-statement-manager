package com.account.statement.manager.service;

import com.account.statement.manager.dto.AccountStatementResponse;
import com.account.statement.manager.dto.StatementRequest;
import com.account.statement.manager.dto.error.ApiError;
import com.account.statement.manager.entity.Account;
import com.account.statement.manager.entity.Statement;
import com.account.statement.manager.enums.ErrorEnum;
import com.account.statement.manager.exception.ResourceNotFoundException;
import com.account.statement.manager.mapper.StatementMapper;
import com.account.statement.manager.repository.AccountRepository;
import com.account.statement.manager.repository.StatementRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for statement related operations.
 *
 * @author Sujith K V
 */
@Service
public class StatementServiceImpl implements StatementService {
    @Autowired
    StatementRepository statementRepository;
    @Autowired
    AccountRepository accountRepository;

    /**
     * Method to get statements from the repository based on the request filtering conditions.
     */
    @Override
    public AccountStatementResponse getStatements(final StatementRequest request) {
        AccountStatementResponse response = null;
        Optional<Account> account = accountRepository.getAccountByAccountId(request.getAccountId());

        if (account.isPresent()) {
            List<Statement> statements = statementRepository.getStatementsByAccountId(account.get().getId());
            List<Statement> filteredStatements = statements.stream()
                    .filter(statement -> statement.getDateField().isAfter(request.getFromDate()))
                    .filter(statement -> statement.getDateField().isBefore(request.getToDate()))
                    .filter(statement -> statement.getAmount() > request.getFromAmount())
                    .filter(statement -> statement.getAmount() < request.getToAmount()).toList();

            response = AccountStatementResponse.builder().accountId(account.get().getId())
                    .accountNumber(DigestUtils.sha256Hex(account.get().getAccountNumber()))
                    .statements(StatementMapper.map(filteredStatements)).build();
        } else {
            throw new ResourceNotFoundException(new ApiError(ErrorEnum.APP404001));
        }

        return response;
    }
}