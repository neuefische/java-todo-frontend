package com.example.backend.service;

import com.example.backend.model.Status;
import com.example.backend.model.ToDoItem;
import com.example.backend.repository.ToDoRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToDoServiceTest {

    ToDoItem item1;
    ToDoRepo toDoRepo, nonMockRepo;
    ToDoService toDoService;

    @BeforeEach
    void setUp() {
        item1 = new ToDoItem("FirstToDo", Status.OPEN, "ID1" );
        toDoRepo = mock(ToDoRepo.class);
        nonMockRepo = new ToDoRepo(new HashMap<>());
        toDoService = new ToDoService(toDoRepo);
    }

    @Test
    void addToDo() { //TODO: How to mock this?
        //GIVEN
        ToDoItem expectedItem = item1;
        toDoService = new ToDoService(toDoRepo);
        //WHEN
        when(toDoRepo.addToDo(item1)).thenReturn(item1);
        ToDoItem actualItem = toDoService.addToDo(item1);
        //THEN
        verify(toDoRepo).addToDo(item1);
        assertEquals(expectedItem.description(), actualItem.description());
        assertEquals(expectedItem.status(), actualItem.status());
    }
    @Test
    void getAllToDoTest(){
        //GIVEN
        toDoService = new ToDoService(new ToDoRepo(new HashMap<>(Map.of("ID1", item1))));
        ToDoItem[] expectedToDos = {item1};
        //WHEN
        ToDoItem[] actualToDos = toDoService.getAllToDos();
        //THEN
        assertArrayEquals(expectedToDos, actualToDos);

    }
    @Test
    void getToDoByIdTest(){
        //GIVEN
        toDoService = new ToDoService(toDoRepo);
        ToDoItem expectedItem = item1;
        //WHEN
        when(toDoRepo.getToDoById(item1.id())).thenReturn(item1);
        ToDoItem actualItem = toDoService.getToDoById("ID1");
        //THEN
        verify(toDoRepo).getToDoById(item1.id());
        assertEquals(expectedItem, actualItem);
    }
    @Test
    void deleteToDoByIdExists(){
        //GIVEN
        toDoService = new ToDoService(toDoRepo);
        ToDoItem expectedItem = item1;
        //WHEN
        when(toDoRepo.deleteToDoById(expectedItem.id())).thenReturn(expectedItem);
        ToDoItem actuallyDeletedItem = toDoService.deleteToDoById(expectedItem.id());
        //THEN
        verify(toDoRepo).deleteToDoById(expectedItem.id());
        assertEquals(expectedItem, actuallyDeletedItem);
    }
    @Test
    void putExistingToDoItem(){
        //GIVEN
        toDoService = new ToDoService(toDoRepo);
        ToDoItem targetedItem = item1;
        ToDoItem expectedPutItem = new ToDoItem("changedDescription", item1.status(), item1.id());
        //WHEN
        when(toDoRepo.getToDoById(targetedItem.id())).thenReturn(targetedItem);
        when(toDoRepo.deleteToDoById(targetedItem.id())).thenReturn(targetedItem);
        when(toDoRepo.addToDo(expectedPutItem)).thenReturn(expectedPutItem);
        ToDoItem actualItem = toDoService.putToDo(expectedPutItem);
        //THEN
        verify(toDoRepo).deleteToDoById(targetedItem.id());
        verify(toDoRepo).addToDo(expectedPutItem);
        verify(toDoRepo).getToDoById(expectedPutItem.id());
        assertEquals(expectedPutItem, actualItem);
    }
}