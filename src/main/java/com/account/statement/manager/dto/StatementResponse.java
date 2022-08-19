package com.account.statement.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * DTO class for statement response.
 *
 * @author Sujith k V
 */
@Data
@AllArgsConstructor
@Builder
public class StatementResponse {
    private Long id;
    private LocalDate dateField;
    private Double amount;
}