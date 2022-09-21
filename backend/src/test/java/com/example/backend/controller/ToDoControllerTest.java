package com.example.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ToDoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getAllToDos_returnsAllToDos()
            throws Exception {

        String expectedJson = """
                [{"id":"1","description":"coden","status":"OPEN"},{"id":"2","description":"essen","status":"OPEN"},{"id":"3","description":"schlafen","status":"DONE"}]
                """;

        mvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    void postNewToDo_returnsToDoById()
            throws Exception {

        String requestBody = """
                {
                "description":"coden",
                "status":"OPEN"
                }
                """;
        String expectedResponseBody = """
                {
                "id":"4",
                "description":"coden",
                "status":"OPEN"
                }
                """;
        mvc.perform(
                        post("/api/todo")
                                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseBody));
    }

    @Test
    void getToDoByID()
            throws Exception {
        String expectedJson = """
                    {"id":"1","description":"coden","status":"OPEN"}
                """;
        mvc.perform(get("/api/todo/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    void putToDoById()
        throws Exception {

            String requestBody = """
                {"id":"1","description":"coden","status":"DONE"}
                """;
            String expectedResponseBody = """
                {"id":"1","description":"coden","status":"DONE"}
                """;
            mvc.perform(
                            put("/api/todo/1")
                                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                    .content(requestBody))
                    .andExpect(status().isOk())
                    .andExpect(content().json(expectedResponseBody));
        }

    @Test
    void deleteToDoById()
            throws Exception {



    }
}