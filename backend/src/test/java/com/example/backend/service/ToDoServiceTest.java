package com.example.backend.service;

import com.example.backend.model.ToDo;
import com.example.backend.repo.ToDoRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ToDoServiceTest {

    ToDoRepo toDoRepo = mock(ToDoRepo.class);
    ToDoService toDoService = new ToDoService(toDoRepo);

    @Test
    void getAllToDos_ReturnsAllToDos() {

        //GIVEN
        List<ToDo> toDos = new ArrayList<>();
                Map.of(
                        "1", new ToDo("Wash the car", "1", "OPEN"));

        when(toDoRepo.getAllToDos()).thenReturn(toDos);

        //WHEN
        List<ToDo> actual = toDoService.getAllToDos();


        //THEN
        assertEquals(toDos, actual);




    }
}