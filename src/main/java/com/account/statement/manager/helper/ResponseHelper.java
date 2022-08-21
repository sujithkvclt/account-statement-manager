package com.account.statement.manager.helper;

import com.account.statement.manager.dto.AccountStatementResponse;
import com.account.statement.manager.dto.error.ApiResponse;
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

        return new ResponseEntity<>(new ApiResponse<>(data), HttpStatus.OK);
    }
}