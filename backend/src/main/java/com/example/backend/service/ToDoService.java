package com.example.backend.service;

import com.example.backend.model.ToDoItem;
import com.example.backend.repository.ToDoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepo toDoRepo;

    public ToDoItem addToDo(ToDoItem addedItem) {
        ToDoItem bookWithId = new ToDoItem(addedItem.description(), addedItem.status(), IDService.generateID());
        return toDoRepo.addToDo(bookWithId);
    }
    public ToDoItem[] getAllToDos(){

    }

}
