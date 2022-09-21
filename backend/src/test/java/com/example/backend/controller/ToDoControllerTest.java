package com.example.backend.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllToDos() throws Exception {

        // GIVEN
        String expectedJson= """
                [
                    {
                        "description": "Wash the car",
                        "status": "OPEN",
                        "id": "1"
                    }
                ]
                """;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson));
    }
}