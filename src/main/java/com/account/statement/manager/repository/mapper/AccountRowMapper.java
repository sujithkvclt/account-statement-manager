package com.account.statement.manager.repository.mapper;

import com.account.statement.manager.entity.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Row Mapper class for Account table
 *
 * @author Sujith K V
 */
public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Account(rs.getLong("id"), rs.getString("account_type"), rs.getString("account_number"));
    }
}