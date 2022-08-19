package com.account.statement.manager.exception;

import com.account.statement.manager.dto.error.ApiError;
import lombok.Getter;

/**
 * Exception class to handle not found exceptions
 *
 * @author Sujith k V
 */
@Getter
public class ResourceNotFoundException extends RuntimeException {
    private final ApiError apiError;

    public ResourceNotFoundException(ApiError apiError) {
        super(apiError.getMessage());
        this.apiError = apiError;
    }
}