package com.account.statement.manager.repository;

import com.account.statement.manager.entity.Account;
import com.account.statement.manager.repository.mapper.AccountRowMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class AccountRepositoryTest {
    @MockBean
    JdbcTemplate jdbcTemplate;
    @MockBean
    AccountRowMapper accountRowMapper;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    @DisplayName("Test getAccountByAccountId")
    void getAccountByAccountIdTestSuccess() {
        Account mockAccount = new Account(1L, "current", "0012250016001");

        when(jdbcTemplate.query(eq("SELECT * FROM account WHERE id = ?"), Mockito.any(AccountRowMapper.class), eq(1L))).thenReturn(getAccounts());

        Optional<Account> account = accountRepository.getAccountByAccountId(1L);

        Assertions.assertEquals("0012250016001", account.get()
                .getAccountNumber(), "Value of accountNumber should be '0012250016001'.");
        Assertions.assertEquals("current", account.get().getAccountType(), "Value of accountTye should be 'current'.");
        Assertions.assertEquals(1L, account.get().getId(), "Value of id should be '1'.");
    }

    private List<Account> getAccounts() {
        Account account1 = new Account(1L, "current", "0012250016001");
        Account account2 = new Account(2L, "savings", "0012250016002");
        Account account3 = new Account(3L, "current", "0012250016003");
        Account account4 = new Account(4L, "current", "0012250016004");
        Account account5 = new Account(5L, "savings", "0012250016005");

        return Arrays.asList(account1, account2, account3, account4, account5);
    }

    @Test
    @DisplayName("Test getAccounts")
    void getAccountsTestSuccess() {
        when(jdbcTemplate.query(eq("SELECT * FROM account"), Mockito.any(AccountRowMapper.class))).thenReturn(getAccounts());

        List<Account> accounts = accountRepository.getAccounts();

        Assertions.assertEquals(5, accounts.size(), "getAccounts should return 5 accounts");
    }
}