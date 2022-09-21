package com.example.backend.service;

import com.example.backend.model.ToDo;
import com.example.backend.repo.ToDoRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ToDoServiceTest {

    ToDoRepo toDoRepo = mock(ToDoRepo.class);

    ToDoService service = new ToDoService(toDoRepo);

    @Test
    void getAllToDos_shouldReturnWholeListOfToDos() {
        ToDo todo1 = new ToDo();
        ToDo todo2 = new ToDo();
        List<ToDo> toDoList = new ArrayList<>(List.of(todo1, todo2));
        when(toDoRepo.getAllToDos()).thenReturn(toDoList);

        List<ToDo> actual = service.getAllToDos();

        assertEquals(List.of(todo1, todo2), actual);
    }

    @Test
    void postNewToDo() {
    }

    @Test
    void getToDoById() {
    }

    @Test
    void putToDo() {
    }

    @Test
    void deleteToDoById() {
    }
}