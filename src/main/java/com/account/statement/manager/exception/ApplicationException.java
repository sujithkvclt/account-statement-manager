package com.account.statement.manager.exception;

import com.account.statement.manager.dto.error.ApiError;
import lombok.Getter;


/**
 * Exception class to handle business exceptions.
 *
 * @author Sujith k V
 */
@Getter
public class ApplicationException extends RuntimeException {
    private final ApiError apiError;

    public ApplicationException(ApiError apiError) {
        super(apiError.getMessage());
        this.apiError = apiError;
    }
}