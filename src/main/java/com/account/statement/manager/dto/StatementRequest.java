package com.account.statement.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * DTO class for get statements API request .
 *
 * @author Sujith k V
 */
@Data
@Builder
@AllArgsConstructor
public class StatementRequest {
    private Long accountId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Double fromAmount;
    private Double toAmount;
}