package com.account.statement.manager.dto.error;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO class for HTTP error response details.
 *
 * @author Sujith k V
 */
@Data
@Builder
public class ErrorDetail implements Serializable {
    protected String message;
    protected String field;
}