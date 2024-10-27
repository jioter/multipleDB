package com.multipledb.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

class UserControllerTest extends TestTemplate {

    @Autowired
    MockMvc mvc;

    @Test
    public void shouldAggregateAllUsersFromAllDatabases() throws Exception {
        mvc.perform(get("/users"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value("1"))
            .andExpect(jsonPath("$[0].username").value("TestUser1"))
            .andExpect(jsonPath("$[0].name").value("TestUser1"))
            .andExpect(jsonPath("$[0].surname").value("TestUser1"))
            .andExpect(jsonPath("$[1].id").value("2"))
            .andExpect(jsonPath("$[1].username").value("TestUser2"))
            .andExpect(jsonPath("$[1].name").value("TestUser2"))
            .andExpect(jsonPath("$[1].surname").value("TestUser2"))
            .andExpect(jsonPath("$[2].id").value("3"))
            .andExpect(jsonPath("$[2].username").value("TestUser3"))
            .andExpect(jsonPath("$[2].name").value("TestUser3"))
            .andExpect(jsonPath("$[2].surname").value("TestUser3"))
            .andExpect(jsonPath("$[3].id").value("4"))
            .andExpect(jsonPath("$[3].username").value("TestUser4"))
            .andExpect(jsonPath("$[3].name").value("TestUser4"))
            .andExpect(jsonPath("$[3].surname").value("TestUser4"))
            .andExpect(jsonPath("$[4].id").value("5"))
            .andExpect(jsonPath("$[4].username").value("TestUser5"))
            .andExpect(jsonPath("$[4].name").value("TestUser5"))
            .andExpect(jsonPath("$[4].surname").value("TestUser5"))
            .andExpect(jsonPath("$[5].id").value("6"))
            .andExpect(jsonPath("$[5].username").value("TestUser6"))
            .andExpect(jsonPath("$[5].name").value("TestUser6"))
            .andExpect(jsonPath("$[5].surname").value("TestUser6"));

    }
}