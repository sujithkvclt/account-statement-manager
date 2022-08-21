package com.account.statement.manager.enums;

import com.account.statement.manager.constant.ErrorMessages;
import lombok.Getter;

/**
 * Enum for error response and error message mapping.
 *
 * @author Sujith k V
 */
@Getter
public enum ErrorEnum {

    APP400001("400-001", ErrorMessages.BAD_REQUEST),
    APP404001("404-001", ErrorMessages.ACCOUNT_NOT_FOUND),
    APP404002("404-002", ErrorMessages.STATEMENTS_NOT_FOUND),
    APP500001("500-001", ErrorMessages.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;

    private ErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}