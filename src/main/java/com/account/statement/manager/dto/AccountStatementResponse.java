package com.account.statement.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO class for account statement response.
 *
 * @author Sujith k V
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountStatementResponse {
    private Long accountId;
    private String accountNumber;
    private List<StatementResponse> statements;
}