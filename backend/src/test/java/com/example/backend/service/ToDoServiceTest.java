package com.example.backend.service;

import com.example.backend.model.Status;
import com.example.backend.model.ToDoItem;
import com.example.backend.model.ToDoItemPostDTO;
import com.example.backend.model.ToDoItemPutDTO;
import com.example.backend.repository.ToDoRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Principal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
class ToDoServiceTest {

    ToDoItem item1;
    ToDoItemPostDTO item1PostDTO;
    ToDoItemPutDTO item1PutDTO;
    Principal principal = mock(Principal.class);
    @Autowired
    ToDoRepo toDoRepo;
    @Autowired
    ToDoService toDoService;
    IDService idService = mock(IDService.class);


    @BeforeEach
    void setUp() {
        item1 = new ToDoItem("FirstToDo", Status.OPEN, "ID1", "user");
        item1PostDTO = new ToDoItemPostDTO("FirstToDo", Status.OPEN);
        item1PutDTO = new ToDoItemPutDTO("FirstToDo", Status.OPEN, "ID1" );
        toDoService = new ToDoService(toDoRepo, idService);
        when(principal.getName()).thenReturn("user");
    }

    @Test
    void addToDo() {
        //GIVEN
        ToDoItem expectedItem = item1;
        //WHEN
        when(idService.generateID()).thenReturn("ID1");
        ToDoItem actualItem = toDoService.addToDo(item1PostDTO, principal);
        //THEN
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
        List<ToDoItem> actualToDos = toDoService.getAllToDos(principal);
        //THEN
        assertEquals(expectedToDos, actualToDos);

    }
    @Test
    void getToDoByIdTest(){
        //GIVEN
        toDoService = new ToDoService(toDoRepo, idService);
        toDoRepo.save(item1);
        ToDoItem expectedItem = item1;
        //WHEN
        ToDoItem actualItem = toDoService.getToDoById("ID1");
        //THEN
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
        assertEquals(expectedItem, actuallyDeletedItem);
    }
    @Test
    void putExistingToDoItem(){
        //GIVEN
        toDoService = new ToDoService(toDoRepo, idService);
        ToDoItem expectedPutItem = item1;
        //WHEN
        ToDoItem actualItem = toDoService.putToDo(item1PutDTO);
        //THEN
        assertEquals(expectedPutItem, actualItem);
    }
}