package com.example.backend.controller;

import com.example.backend.model.ToDoItem;
import com.example.backend.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class ToDoController {
    private final ToDoService toDoService;

    @PostMapping("todo")
    public ToDoItem ToDoItem (@RequestBody ToDoItem postedItem) {
        return toDoService.addToDo(postedItem);
    }
    @GetMapping("todo")
    public ToDoItem[] getAllToDos(){
        return toDoService.getAllToDos();
    }
}
