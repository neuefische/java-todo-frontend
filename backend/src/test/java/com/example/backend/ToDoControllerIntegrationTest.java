package com.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class ToDoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addNewTodoAndExpectToRecieveToDoBack() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                     {
                                     "id": null,
                                     "description": "water your plants",
                                     "status": "done"
                                     }
                                """)).andExpect(status().isOk())

                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                                       {
                                       "description": "water your plants",
                                               "status": "done"
                                       }
                        """)).andExpect(jsonPath("id").isNotEmpty());
    }

    @Test
    void getAllTodosAndExpectStatus200AndExpectedToDo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                              [
                                {
                                    "id": "1",
                                    "description": "water plants",
                                    "status": "done"
                                }  
                              ]                                
                        """));
    }

    @Test
    void postToDoAndGetToDoByIdAndExpectStatus200AndExpectedTodo() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": "1",
                            "description": "water plants",
                            "status": "done"
                        }
                        """));


    }

    @Test
    void changeToDoByIdAndExpect() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/api/todo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                                        {
                                    "id": "1",
                                    "description": "collect water",
                                    "status": "done"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                                                        {
                                                    "id": "1",
                                                    "description": "collect water",
                                                    "status": "done"
                                                }
                        """))
                .andExpect(status().isOk());

    }

    @Test
    void deleteToDoByIdAndExpectStatus200() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/1"))

                .andExpect(status().isOk());
    }

}
