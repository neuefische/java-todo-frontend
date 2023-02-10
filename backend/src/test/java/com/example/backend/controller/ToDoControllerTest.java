package com.example.backend.controller;

import com.example.backend.model.Status;
import com.example.backend.model.ToDoItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ToDoControllerTest {
    ToDoItem item1;
    @Autowired
    MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        ToDoItem item1 = new ToDoItem("FirstToDo", Status.OPEN, "ID1");
    }

    @Test
    @DirtiesContext
    void toDoItem() throws Exception {
        //GIVEN
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "description": "FirstToDo",
                        "status": "OPEN"
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                {
                        "description": "FirstToDo",
                        "status": "OPEN"
                }
                """));
    }
}