package com.example.backend.service;

import com.example.backend.model.ToDoItem;
import com.example.backend.repository.ToDoRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ToDoService {
    private final ToDoRepo toDoRepo;
    private final  IDService idService;

    public ToDoItem addToDo(ToDoItem addedItem) {
        ToDoItem itemWithId = new ToDoItem(addedItem.description(), addedItem.status(), idService.generateID());
        return toDoRepo.addToDo(itemWithId);
    }
    public ToDoItem[] getAllToDos(){
        return toDoRepo.getAllToDoItems();
    }
    public ToDoItem getToDoById(String id){
        return toDoRepo.getToDoById(id);
    }
    public ToDoItem deleteToDoById(String id){
        Optional<ToDoItem> optionalOfRequestedItem = Optional.ofNullable(toDoRepo.getToDoById(id));
        if(optionalOfRequestedItem.isPresent()){
            return toDoRepo.deleteToDoById(id);
        } else {
            throw new NoSuchElementException("Requested ToDo with ID: "+id+" does not exist!");
        }
    }
    public ToDoItem putToDo(ToDoItem requestedPutItem){
        Optional<ToDoItem> optionalOfRequestedItem = Optional.ofNullable(
                toDoRepo.getToDoById(requestedPutItem.id()));
        if(optionalOfRequestedItem.isPresent()){
            return toDoRepo.addToDo(requestedPutItem);
        } else {
          throw new NoSuchElementException("Requested ToDo with ID: "+requestedPutItem.id()+
                  " does not exist!");
        }
    }
}
