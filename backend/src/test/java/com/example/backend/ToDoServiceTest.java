package com.example.backend;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToDoServiceTest {

    private final ToDoRepo toDoRepo = mock(ToDoRepo.class);
    private final UuidService uuidService = mock(UuidService.class);
    private final ToDoService toDoService = new ToDoService(toDoRepo, uuidService);

    @Test
    @DisplayName("Add a Todo with no id and expect the ToDo to have a Id afterwards ")
    void addTodo() {
        //GIVEN
        ToDo test = new ToDo("", "description", "done");
        ToDo expected = new ToDo("100", "description", "done");

        //WHEN
        when(uuidService.getRandomId()).thenReturn("100");
        when(toDoRepo.addTodo(test)).thenReturn(expected);
        ToDo actual = toDoService.addTodo(test);

        //THEN
        verify(toDoRepo).addTodo(expected);
        verify(uuidService).getRandomId();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get list with all Todos")
    void getAllTodos() {
        //GIVEN
        List<ToDo> expected = List.of(new ToDo("1", "water the plants", "done"));

        //WHEN
        when(toDoRepo.getAllTodos()).thenReturn(expected);
        List<ToDo> actual = toDoService.getAllTodos();

        //THEN
        verify(toDoRepo).getAllTodos();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get ToDo by Id and expect the awaited Todo")
    void getToDoById() {
        //GIVEN
        ToDo expected = new ToDo("100", "description", "done");
        //WHEN
        when(toDoRepo.getTodoById("100")).thenReturn(expected);
        ToDo actual = toDoService.getTodoById("100");
        //THEN
        assertEquals(expected, actual);

    }

    @Test
    void upDateToDoById() {
        //GIVEN
        ToDo toDo = new ToDo("100", "description", "done");
        ToDo expected = new ToDo("100", "water your plants", "done");
        //WHEN
        when(toDoRepo.getTodoById("100")).thenReturn(toDo);
        ToDo actual = toDoService.updateTodoById("100", expected);
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test deleteById with no existing id and expect NoSuchElementException ")
    void deleteById() {
        //WHEN
        when(toDoRepo.getTodoById("1")).thenReturn(null);
        //THEN
        assertThrows(NoSuchElementException.class, () -> toDoService.deleteById("101"), "No Element with this ID");
    }


}
