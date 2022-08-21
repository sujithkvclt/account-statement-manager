package com.account.statement.manager.controller;

import com.account.statement.manager.dto.StatementResponse;
import com.account.statement.manager.entity.Account;
import com.account.statement.manager.entity.Statement;
import com.account.statement.manager.mapper.StatementMapper;
import com.account.statement.manager.repository.AccountRepository;
import com.account.statement.manager.repository.StatementRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StatementControllerApiTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccountRepository accountRepository;

    @MockBean
    StatementRepository statementRepository;

    @Test
    @DisplayName("Authorization failure Test")
    void authorizationFailureTest() throws Exception {
        mockMvc.perform(get("/statements/1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("ADMIN authorization success Test")
    void adminAuthorizationSuccessTest() throws Exception {
        when(accountRepository.getAccountByAccountId(1L)).thenReturn(Optional.of(new Account(1L, "savings", "1987657")));
        when(statementRepository.getStatementsByAccountId(1L)).thenReturn(getStatements());
        mockMvc.perform(get("/statements/1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .with(user("test").password("test").roles("ADMIN")).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("USER authorization failure Test")
    void userAuthorizationFailureTest() throws Exception {
        mockMvc.perform(get("/statements/1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .with(user("test").password("test").roles("USER")).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("USER authorization success Test")
    void userAuthorizationSuccessTest() throws Exception {
        when(accountRepository.getAccountByAccountId(1L)).thenReturn(Optional.of(new Account(1L, "savings", "1987657")));
        when(statementRepository.getStatementsByAccountId(1L)).thenReturn(getStatements()
        );
        mockMvc.perform(get("/statements/user/1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .with(user("test").password("test").roles("USER")).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Invalid fromDate request param Test")
    void invalidFromDateRequestParam() throws Exception {
        mockMvc.perform(get("/statements/1?fromDate=30102022").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").with(user("test").password("test").roles("ADMIN"))
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    private List<Statement> getStatements() {
        Statement statement1 = new Statement(1L, 1L, LocalDate.of(2022, 06, 01), Double.valueOf(100.000));
        Statement statement2 = new Statement(2L, 1L, LocalDate.of(2021, 05, 11), Double.valueOf(90.3245));
        Statement statement3 = new Statement(3L, 1L, LocalDate.of(2021, 06, 11), Double.valueOf(1000.54325));
        Statement statement4 = new Statement(4L, 1L, LocalDate.of(2020, 03, 18), Double.valueOf(75.123));

        return Arrays.asList(statement1, statement2, statement3, statement4);
    }
}