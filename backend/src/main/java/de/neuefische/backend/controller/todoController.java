package de.neuefische.backend.controller;

import de.neuefische.backend.model.TodoElement;
import de.neuefische.backend.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class todoController {

    private final TodoService service;

    @GetMapping
    public List<TodoElement> getAllTodo(){
        return service.getAllTodo();
    }

}
