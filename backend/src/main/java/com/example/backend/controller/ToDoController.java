package com.example.backend.controller;

import com.example.backend.model.ToDoItem;
import com.example.backend.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class ToDoController {
    private final ToDoService toDoService;

    @PostMapping("todo")
    public ToDoItem postToDoItem (@RequestBody ToDoItem postedItem) {
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
    public ToDoItem putToDoItem(@RequestBody ToDoItem itemToPut){
        return toDoService.putToDo(itemToPut);
    }
}
