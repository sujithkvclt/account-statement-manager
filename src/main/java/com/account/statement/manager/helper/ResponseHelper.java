package com.account.statement.manager.helper;

import com.account.statement.manager.dto.AccountStatementResponse;
import com.account.statement.manager.dto.error.ApiError;
import com.account.statement.manager.dto.error.ApiResponse;
import com.account.statement.manager.enums.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Helper class to prepare HTTP response objects based on the service response.
 *
 * @author Sujith k V
 */
@Slf4j
@Component
public class ResponseHelper {
    private ResponseHelper() {
    }

    /**
     * Method to create HTTP response object with wrapper classes.
     */
    public static ResponseEntity<ApiResponse<AccountStatementResponse>> creteStatementResponse(
            final AccountStatementResponse data) {
        ApiResponse<AccountStatementResponse> apiResponse = null;

        if (data.getStatements().isEmpty()) {
            ApiError error = new ApiError(ErrorEnum.APP200001);

            apiResponse = new ApiResponse<>(data, error);
        } else {
            apiResponse = new ApiResponse<>(data);
        }

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}