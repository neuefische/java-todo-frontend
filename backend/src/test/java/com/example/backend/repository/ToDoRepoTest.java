package com.example.backend.repository;

import com.example.backend.model.Status;
import com.example.backend.model.ToDoItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ToDoRepoTest {
    ToDoItem item1;
    ToDoRepo toDoRepo;
    @BeforeEach
    void setUp() {
        item1 = new ToDoItem("FirstToDo", Status.OPEN, "ID1" );
        toDoRepo = new ToDoRepo(new HashMap<>());
    }


}