package com.example.backend.controller;

import com.example.backend.model.ToDoItem;
import com.example.backend.model.ToDoItemPostDTO;
import com.example.backend.model.ToDoItemPutDTO;
import com.example.backend.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class ToDoController {
    private final ToDoService toDoService;

    @PostMapping("todo")
    public ToDoItem postToDoItem (@RequestBody ToDoItemPostDTO postedItem, Principal principal) {
        return toDoService.addToDo(postedItem, principal);
    }
    @GetMapping("todo")
    public List<ToDoItem> getAllToDos(Principal principal){
        return toDoService.getAllToDos(principal);
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
}