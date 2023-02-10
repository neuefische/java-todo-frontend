package com.example.backend.service;

import com.example.backend.model.ToDoItem;
import com.example.backend.repository.ToDoRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ToDoService {
    private final ToDoRepo toDoRepo;
    private final  IDService idService;

    public ToDoItem addToDo(ToDoItem addedItem) {
        ToDoItem bookWithId = new ToDoItem(addedItem.description(), addedItem.status(), idService.generateID());
        return toDoRepo.addToDo(bookWithId);
    }
    public ToDoItem[] getAllToDos(){
        return toDoRepo.getAllToDoItems();
    }
    public ToDoItem getToDoById(String id){
        return toDoRepo.getToDoById(id);
    }
    public ToDoItem deleteToDoById(String id){
        return toDoRepo.deleteToDoById(id);
    }
    public ToDoItem putToDo(ToDoItem requestedPutItem){
        Optional<ToDoItem> optionalOfRequestedItem = Optional.ofNullable(
                toDoRepo.getToDoById(requestedPutItem.id()));
        if(optionalOfRequestedItem.isPresent()){
            toDoRepo.deleteToDoById(requestedPutItem.id());
            return toDoRepo.addToDo(requestedPutItem);
        } else {
          throw new NoSuchElementException("Requested ToDo with ID: "+requestedPutItem.id()+
                  " does not exist!");
        }
    }
}
