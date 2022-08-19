package com.account.statement.manager.repository;

import com.account.statement.manager.entity.Statement;
import com.account.statement.manager.repository.mapper.StatementRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository class for Statement table
 *
 * @author Sujith K V
 */
@Repository
public class StatementRepositoryImpl implements StatementRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Method to get all statements
     *
     * @return list of statements
     */
    @Override
    public List<Statement> getStatements() {
        return jdbcTemplate.query("SELECT * FROM statement", new StatementRowMapper());
    }

    /**
     * Method to get statement by accountId
     *
     * @param accountId
     * @return list of statements
     */
    @Override
    public List<Statement> getStatementsByAccountId(Long accountId) {
        return jdbcTemplate.query("SELECT * FROM statement WHERE account_id = ?", new StatementRowMapper(), accountId);
    }
}