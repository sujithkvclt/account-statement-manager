package com.account.statement.manager.repository;

import com.account.statement.manager.entity.Account;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Account table
 *
 * @author Sujith K V
 */
public interface AccountRepository {
    Optional<Account> getAccountByAccountId(final Long accountId);

    List<Account> getAccounts();
}