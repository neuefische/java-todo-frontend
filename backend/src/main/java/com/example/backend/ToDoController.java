package com.example.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;

    @PostMapping
    public ToDo addTodo(@RequestBody ToDo newTodo) {
        return toDoService.addTodo(newTodo);
    }

    @GetMapping
    public List<ToDo> getAllTodos() {
        return toDoService.getAllTodos();
    }

    @GetMapping("/{id}")
    public ToDo getTodoById(@PathVariable String id) {
        return toDoService.getTodoById(id);
    }

    @PutMapping("/{id}")
    public ToDo updateTodoById(@PathVariable String id, @RequestBody ToDo todo) {
        return toDoService.updateTodoById(id, todo);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        toDoService.deleteById(id);
    }
}
