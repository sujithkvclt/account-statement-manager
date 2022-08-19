package com.account.statement.manager.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StatementControllerApiTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Authorization failure Test")
    void authorizationFailureTest() throws Exception {
        mockMvc.perform(get("/statements/1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("ADMIN authorization success Test")
    void adminAuthorizationSuccessTest() throws Exception {
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
}