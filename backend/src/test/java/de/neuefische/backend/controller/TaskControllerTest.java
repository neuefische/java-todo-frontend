package de.neuefische.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.backend.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.ServletRequestBindingException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DirtiesContext
    void whenGetAllTask_returnTheInitialEmptyListOfTasks_return200OK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DirtiesContext
    void whenGetTaskById_thenReturnCorrectTask_andStatus200OK() throws Exception {
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                .contentType("application/json")
                .content("""
                        {
                            "description": "Buy milk",
                            "status": "OPEN"
                        }
                        """)).andReturn();

        String content = response.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        Task task = objectMapper.readValue(content, Task.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/" + task.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "description": "Buy milk",
                            "status": "OPEN"
                        }
                        """)).andExpect(jsonPath("$.id").value(task.getId()));
    }

    @Test
    @DirtiesContext
    void whenGetTaskByIdWithInvalidId_thenThrowIllegalArgumentException_andStatus400BadRequest() throws Exception {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/invalid-id"));
            fail();
        } catch (Exception e) {
            assertEquals("Task with id invalid-id does not exist", e.getCause().getMessage());
        }
    }

    @Test
    @DirtiesContext
    void whenAddNewTask_thenReturnCreatedTask_andStatusCode200OK() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "description": "Buy milk",
                                    "status": "OPEN"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "description": "Buy milk",
                            "status": "OPEN"
                        }
                        """)).andExpect(jsonPath("$.id").isNotEmpty());
    }

    @Test
    @DirtiesContext
    void whenUpdateTask_thenReturnUpdatedTask_andStatusCode200OK() throws Exception {
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "description": "Buy Milk",
                                    "status": "OPEN"
                                }
                                """)).andReturn();

        String content = response.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        Task task = objectMapper.readValue(content, Task.class);

        System.out.println(task.getId());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/todo/" + task.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content.replace("OPEN", "DONE")))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "description": "Buy Milk",
                            "status": "DONE"
                        }
                        """));
    }

    @Test
    @DirtiesContext
    void whenUpdateTaskWithNotMatchingIds_thenThrowException(){
        try {
            mockMvc.perform(MockMvcRequestBuilders.put("/api/todo/invalid-id")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("""
                                    {
                                        "id" : "12345566",
                                        "description": "Buy Milk",
                                        "status": "OPEN"
                                    }
                                    """));
            fail();
        } catch (Exception e) {
            assertEquals("Id of task and id in path do not match", e.getCause().getMessage());
        }
    }
    @Test
    @DirtiesContext
    void whenDeletingTaskWithValidId_returnDeletedTask_andStatusCode200OK() throws Exception {
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                                {
                                    "description": "Buy Milk",
                                    "status": "OPEN"
                                }
                                """)).andReturn();

        String content = response.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        Task task = objectMapper.readValue(content, Task.class);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/" + task.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "description": "Buy Milk",
                            "status": "OPEN"
                        }
                        """)).andExpect(jsonPath("$.id").value(task.getId()));
    }

    @Test
    @DirtiesContext
    void whenDeletingTaskWithInvalidId_thenThrowException() throws Exception {
        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/invalid-id"));
            fail();
        } catch (Exception e) {
            assertEquals("Task with id invalid-id does not exist", e.getCause().getMessage());
        }
    }
}