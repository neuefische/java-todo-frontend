package de.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TaskIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DirtiesContext
    void addTask() throws Exception {
        // GIVEN
        String body = mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"description":"testpost","status":"OPEN"}
                                """))
                .andExpect(status().isOk())

                .andReturn().getResponse().getContentAsString();
// convert string into Task type
        Task task = objectMapper.readValue(body, Task.class);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/" + task.id()))
                // THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {"id":"<id>","description":"testpost","status":"OPEN"}
                        """.replace("<id>", task.id())));


    }

    @Test
    void getAllTasksReturnsEmptyList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        []
                        """));
    }


    @Test
    @DirtiesContext
    void getTaskByIdReturnTask() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"description":"testpost","status":"OPEN"}
                                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {"description":"testpost","status":"OPEN"}
                        """));
    }

    @Test
    @DirtiesContext
    void putRequestUpdateTask() throws Exception {
        // GIVEN
        String body = mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"description":"testpost","status":"OPEN"}
                                """))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
// convert string into Task type
        Task task = objectMapper.readValue(body, Task.class);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders.put("/api/todo/" + task.id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(("""
                                {"id":"<id>","description":"testpost","status":"IN_PROGRESS"}
                                """.replace("<id>", task.id()))))
                // THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {"id":"<id>","description":"testpost","status":"IN_PROGRESS"}
                        """.replace("<id>", task.id())));
    }
    @Test
    @DirtiesContext
    void deleteRequestRemovesTask() throws Exception {
        // GIVEN
        String body = mockMvc.perform(MockMvcRequestBuilders.post("/api/todo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"description":"testpost","status":"OPEN"}
                                """))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        // convert string into Task type
        Task task = objectMapper.readValue(body, Task.class);

        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/" + task.id()))
                // THEN
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        []
                        """));
    }
}