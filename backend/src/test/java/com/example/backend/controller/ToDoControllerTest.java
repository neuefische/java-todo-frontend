package com.example.backend.controller;

import com.example.backend.model.Status;
import com.example.backend.model.ToDoItem;
import com.example.backend.repository.ToDoRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ToDoControllerTest {
    ToDoItem item1;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ToDoRepo toDoRepo;
    @BeforeEach
    void setUp() {
        item1 = new ToDoItem("FirstToDo", Status.OPEN, "ID1", "user");
    }

    @Test
    @DirtiesContext
    @WithMockUser
    void postToDoItem() throws Exception {
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "description": "FirstToDo",
                        "status": "OPEN",
                        "userName": "user"
                    }
                """).with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                {
                        "description": "FirstToDo",
                        "status": "OPEN",
                        "userName": "user"
                }
                """))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }
    @Test
    @DirtiesContext
    @WithMockUser
    void getAllToDos() throws Exception {
        //GIVEN
        toDoRepo.save(item1);
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                [{
                        "id": "ID1",
                        "description": "FirstToDo",
                        "status": "OPEN"
                }]
                """));
    }
    @Test
    @DirtiesContext
    @WithMockUser
    void getToDoByExistingId() throws Exception {
        //GIVEN
        toDoRepo.save(item1);
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/ID1"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                {
                        "id": "ID1",
                        "description": "FirstToDo",
                        "status": "OPEN",
                        "userName": "user"
                }
                """));
    }
    @Test
    @DirtiesContext
    @WithMockUser
    void deleteToDoByExistingId() throws Exception {
        //GIVEN
        toDoRepo.save(item1);
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/ID1")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                {
                        "id": "ID1",
                        "description": "FirstToDo",
                        "status": "OPEN",
                        "userName": "user"
                }
                """));
    }

    @Test
    @DirtiesContext
    @WithMockUser
    void putExistingToDoItem() throws Exception {
        //GIVEN
        toDoRepo.save(item1);
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.put("/api/todo/ID1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "id": "ID1",
                        "description": "modifiedItemDescription",
                        "status": "OPEN"
                    }
                """)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                {
                        "id": "ID1",
                        "description": "modifiedItemDescription",
                        "status": "OPEN",
                        "userName": "user"
                }
                """));
    }
}