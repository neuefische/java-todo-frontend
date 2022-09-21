package com.example.backend.controller;

import com.example.backend.model.ToDo;
import com.example.backend.service.ToDoService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService service;

    @GetMapping
    public List<ToDo> getAllToDos() {
        return service.getAllToDos();
    }

    @PostMapping
    public ToDo postNewToDo(@RequestBody ToDo q) {
        return service.postNewToDo(q);
    }

    @GetMapping("{id}")
    public ToDo getToDoByID(@PathVariable String id) {
        return service.getToDoById(id);
    }

    @PutMapping("{id}")
    public ToDo putToDo(@RequestBody ToDo newToDo) {
        return service.putToDo(newToDo);

    }

    @DeleteMapping("{id}")
    public void deleteToDoById(@PathVariable String id) {
        service.deleteToDoById(id);
    }
}
