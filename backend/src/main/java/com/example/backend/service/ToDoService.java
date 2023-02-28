package com.example.backend.service;

import com.example.backend.model.ToDoItem;
import com.example.backend.repository.ToDoRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ToDoService {
    private final ToDoRepo toDoRepo;
    private final  IDService idService;

    public ToDoItem addToDo(ToDoItem addedItem) {
        ToDoItem itemWithId = new ToDoItem(addedItem.description(), addedItem.status(), idService.generateID());
        return toDoRepo.save(itemWithId);
    }
    public List<ToDoItem> getAllToDos(){
        return toDoRepo.findAll();
    }
    public ToDoItem getToDoById(String id){
        return toDoRepo.findById(id).orElseThrow(NoSuchElementException::new);
    }
    public ToDoItem deleteToDoById(String id){
        Optional<ToDoItem> optionalOfRequestedItem = toDoRepo.findById(id);
        if(optionalOfRequestedItem.isPresent()){
           toDoRepo.deleteById(id);
           return optionalOfRequestedItem.get();
        } else {
            throw new NoSuchElementException("Requested ToDo with ID: "+id+" does not exist!");
        }
    }
    public ToDoItem putToDo(ToDoItem requestedPutItem){
        Optional<ToDoItem> optionalOfRequestedItem = toDoRepo.findById(requestedPutItem.id());
        if(optionalOfRequestedItem.isPresent()){
            return toDoRepo.save(requestedPutItem);
        } else {
          throw new NoSuchElementException("Requested ToDo with ID: "+requestedPutItem.id()+
                  " does not exist!");
        }
    }
}
