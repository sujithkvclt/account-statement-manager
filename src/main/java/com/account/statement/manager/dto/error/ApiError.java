package com.account.statement.manager.dto.error;

import com.account.statement.manager.enums.ErrorEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO class for HTTP error response data.
 *
 * @author Sujith k V
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError implements Serializable {
    private LocalDateTime timestamp;
    private String message;
    private String code;
    private List<ErrorDetail> details;

    public ApiError(ErrorEnum errorEnum) {
        this.code = errorEnum.getCode();
        this.message = errorEnum.getMessage();
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(ErrorEnum errorEnum, List<ErrorDetail> details) {
        this.code = errorEnum.getCode();
        this.message = errorEnum.getMessage();
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }
}