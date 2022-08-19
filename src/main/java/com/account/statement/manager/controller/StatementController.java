package com.account.statement.manager.controller;

import com.account.statement.manager.annotation.Amount;
import com.account.statement.manager.annotation.Date;
import com.account.statement.manager.annotation.Number;
import com.account.statement.manager.dto.AccountStatementResponse;
import com.account.statement.manager.dto.StatementRequest;
import com.account.statement.manager.dto.error.ApiResponse;
import com.account.statement.manager.helper.RequestHelper;
import com.account.statement.manager.helper.ResponseHelper;
import com.account.statement.manager.service.StatementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * The Rest controller class is implemented to provide account statements based on different filtering
 * conditions.
 *
 * @author Sujith K V
 */
@Slf4j
@Validated
@RestController
@RequestMapping("statements")
public class StatementController {

    /**
     * Statement service
     */
    @Autowired
    StatementService statementService;


    /**
     * The method is corresponding to the /{accountNo} end point. Which can be accessed by user's with ADMIN access
     * only. It can accept amount range or date range filter parameters in the request.
     *
     * @param accountId  must not be {@literal null}.
     * @param fromAmount can be {@literal null}.
     * @param toAmount   can be {@literal null}.
     * @param fromDate   can be {@literal null}.
     * @param toDate     can be {@literal null}.
     * @return a <code>ResponseEntity</code> with <code>AccountStatementResponse</code> information.
     */
    @GetMapping("/{accountId}")
    public ResponseEntity<ApiResponse<AccountStatementResponse>> getStatements(
            @PathVariable @NotBlank @Number String accountId, @RequestParam(required = false) @Date String fromDate,
            @RequestParam(required = false) @Date String toDate,
            @RequestParam(required = false) @Amount String fromAmount,
            @RequestParam(required = false) @Amount String toAmount) {
        StatementRequest request = RequestHelper.creteStatementRequest(accountId, fromDate, toDate, fromAmount, toAmount);
        AccountStatementResponse statements = statementService.getStatements(request);

        return ResponseHelper.creteStatementResponse(statements);
    }

    /**
     * The method is corresponding to the /{accountNo} end point. Which can be accessed by user with
     * <code>ADMIN</code> role or
     * <code>USER</code> tole
     *
     * @param accountId must not be {@literal null}.
     * @return a <code>ResponseEntity</code> with <code>AccountStatementResponse</code> information.
     */
    @GetMapping("/user/{accountId}")
    public ResponseEntity<ApiResponse<AccountStatementResponse>> getUserStatements(
            @PathVariable @NotBlank @Number String accountId) {
        StatementRequest request = RequestHelper.creteStatementRequest(accountId);
        AccountStatementResponse statements = statementService.getStatements(request);

        return ResponseHelper.creteStatementResponse(statements);
    }
}
