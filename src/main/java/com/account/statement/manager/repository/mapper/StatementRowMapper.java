package com.account.statement.manager.repository.mapper;

import com.account.statement.manager.constant.ApplicationConstants;
import com.account.statement.manager.entity.Statement;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

import static com.account.statement.manager.util.DateUtil.stringToLocalDate;

/**
 * Row Mapper class for Statement table
 *
 * @author Sujith K V
 */
public class StatementRowMapper implements RowMapper<Statement> {
    @Override
    public Statement mapRow(ResultSet rs, int rowNum) throws SQLException {
        LocalDate date = null;

        if (Objects.nonNull(rs.getString("datefield"))) {
            date = stringToLocalDate(rs.getString("datefield"), ApplicationConstants.DATE_FORMAT_DDMMYYYY);
        }

        Double amount = null;

        if (Objects.nonNull(rs.getString("amount"))) {
            amount = Double.valueOf(rs.getString("amount"));
        }

        return new Statement(rs.getLong("id"), rs.getLong("account_id"), date, amount);
    }
}