package com.example.backend.service;

import com.example.backend.model.Status;
import com.example.backend.model.ToDoItem;
import com.example.backend.repository.ToDoRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToDoServiceTest {

    ToDoItem item1;
    ToDoRepo toDoRepo;
    ToDoService toDoService;
    IDService idService = mock(IDService.class);


    @BeforeEach
    void setUp() {
        item1 = new ToDoItem("FirstToDo", Status.OPEN, "ID1" );
        toDoService = new ToDoService(toDoRepo, idService);
    }

    @Test
    void addToDo() {
        //GIVEN
        ToDoItem expectedItem = item1;
        //WHEN
        when(idService.generateID()).thenReturn("ID1");
        ToDoItem actualItem = toDoService.addToDo(item1);
        //THEN
        verify(toDoRepo).save(item1);
        verify(idService).generateID();
        assertEquals(expectedItem.description(), actualItem.description());
        assertEquals(expectedItem.status(), actualItem.status());
    }
    @Test
    void getAllToDoTest(){
        //GIVEN
        toDoService = new ToDoService(toDoRepo, idService);
        toDoRepo.save(item1);
        List<ToDoItem> expectedToDos = new ArrayList<>(List.of(item1));
        //WHEN
        List<ToDoItem> actualToDos = toDoService.getAllToDos();
        //THEN
        assertEquals(expectedToDos, actualToDos);

    }
    @Test
    void getToDoByIdTest(){
        //GIVEN
        toDoService = new ToDoService(toDoRepo, idService);
        ToDoItem expectedItem = item1;
        //WHEN
        ToDoItem actualItem = toDoService.getToDoById("ID1");
        //THEN
        verify(toDoRepo).findById(item1.id());
        assertEquals(expectedItem, actualItem);
    }
    @Test
    void deleteToDoByIdExists(){
        //GIVEN
        toDoService = new ToDoService(toDoRepo, idService);
        ToDoItem expectedItem = item1;
        //WHEN
        ToDoItem actuallyDeletedItem = toDoService.deleteToDoById(expectedItem.id());
        //THEN
        verify(toDoRepo).deleteById(expectedItem.id());
        assertEquals(expectedItem, actuallyDeletedItem);
    }
    @Test
    void putExistingToDoItem(){
        //GIVEN
        toDoService = new ToDoService(toDoRepo, idService);
        ToDoItem targetedItem = item1;
        ToDoItem expectedPutItem = new ToDoItem("changedDescription", item1.status(), item1.id());
        //WHEN
        ToDoItem actualItem = toDoService.putToDo(expectedPutItem);
        //THEN
        //verify(toDoRepo).deleteToDoById(targetedItem.id());
        verify(toDoRepo).save(expectedPutItem);
        verify(toDoRepo).findById(expectedPutItem.id());
        assertEquals(expectedPutItem, actualItem);
    }
}