package com.account.statement.manager.repository;

import com.account.statement.manager.entity.Account;
import com.account.statement.manager.repository.mapper.AccountRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository class for Account table
 *
 * @author Sujith K V
 */
@Repository
public class AccountRepositoryImpl implements AccountRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Method to get account by accountId
     *
     * @param accountId
     * @return Account
     */
    @Override
    public Optional<Account> getAccountByAccountId(Long accountId) {
        List<Account> accounts = jdbcTemplate.query("SELECT * FROM account WHERE id = ?", new AccountRowMapper(), accountId);

        return accounts.stream().findFirst();
    }

    /**
     * Method to get all account
     *
     * @return list of Account
     */
    @Override
    public List<Account> getAccounts() {
        return jdbcTemplate.query("SELECT * FROM account", new AccountRowMapper());
    }
}