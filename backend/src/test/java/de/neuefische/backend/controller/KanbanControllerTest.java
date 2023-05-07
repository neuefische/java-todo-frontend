package de.neuefische.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.backend.model.ToDo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class KanbanControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getToDos_returnStatus200_printListToDos() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DirtiesContext
    void getToDoById_returnStatus200_returnThisToDo() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {"description": "skdfg", "status": "OPEN"}
                                """))
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        ToDo toDo = objectMapper.readValue(content, ToDo.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/" + toDo.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            {"description": "skdfg", "status": "OPEN"}
                                """))
                .andExpect(jsonPath("$.id").value(toDo.getId()));
    }

    @Test
    @DirtiesContext
    void postToDo_returnStatus200_returnNewToDo() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {"description": "skdfg", "status": "OPEN"}
                                """))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DirtiesContext
    void putToDo_returnStatus200_returnEditedToDo() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {"description": "skdfg", "status": "OPEN"}
                                """))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        ToDo toDo = objectMapper.readValue(content, ToDo.class);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/todo/" + toDo.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {"description": "Test", "status": "OPEN"}
                                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
{"description": "Test", "status": "OPEN"}
"""))
                .andExpect(jsonPath("$.id").value(toDo.getId()));

    }



    @Test
    @DirtiesContext
    void deleteToDo() throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {"description": "skdfg", "status": "OPEN"}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        ToDo toDo = objectMapper.readValue(content, ToDo.class);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/" + toDo.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                            {"description": "skdfg", "status": "OPEN"}
                                """))
                        .andExpect(jsonPath("$.id").isNotEmpty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/" + toDo.getId()))
                .andExpect(status().isNotFound());

    }
}