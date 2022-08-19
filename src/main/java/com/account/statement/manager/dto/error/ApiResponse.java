package com.account.statement.manager.dto.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * DTO wrapper class for HTTP response data.
 *
 * @author Sujith k V
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private T data;
    private ApiError error;

    public ApiResponse(ApiError error) {
        this.error = error;
    }

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(T data, ApiError error) {
        this.data = data;
        this.error = error;
    }
}