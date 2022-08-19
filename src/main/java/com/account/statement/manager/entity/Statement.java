package com.account.statement.manager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entity class for Statement table.
 *
 * @author Sujith k V
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statement {
    private Long id;
    private Long accountId;
    private LocalDate dateField;
    private Double amount;
}