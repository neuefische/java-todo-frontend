package com.example.backend.controller;

import com.example.backend.model.MongoUser;
import com.example.backend.model.ToDoItem;
import com.example.backend.model.ToDoItemPostDTO;
import com.example.backend.model.ToDoItemPutDTO;
import com.example.backend.repository.MongoUserRepository;
import com.example.backend.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class ToDoController {
    private final ToDoService toDoService;
    private final MongoUserRepository mongoUserRepository;

    @PostMapping("todo")
    public ToDoItem postToDoItem (@RequestBody ToDoItemPostDTO postedItem) {
        return toDoService.addToDo(postedItem);
    }
    @GetMapping("todo")
    public List<ToDoItem> getAllToDos(){
        return toDoService.getAllToDos();
    }
    @GetMapping("/todo/{id}")
    public ToDoItem getToDoById(@PathVariable String id){
        return toDoService.getToDoById(id);
    }
    @DeleteMapping("/todo/{id}")
    public ToDoItem deleteToDoById(@PathVariable String id){
        return toDoService.deleteToDoById(id);
    }
    @PutMapping("/todo/{id}")
    public ToDoItem putToDoItem(@RequestBody ToDoItemPutDTO itemToPut){
        return toDoService.putToDo(itemToPut);
    }
    @PostMapping("/")
    public MongoUser createUser(){
        return mongoUserRepository.save(new MongoUser("123", "admin", "1"));
    }
}