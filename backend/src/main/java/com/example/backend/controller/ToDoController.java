package com.example.backend.controller;

import com.example.backend.model.ToDo;
import com.example.backend.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/todo")
public class ToDoController {

    private ToDoService todoService;

    @Autowired
    public ToDoController(ToDoService toDoService){
        this.todoService = toDoService;
    }

    @GetMapping
    public List<ToDo> getAllToDos(){
        return todoService.getAllToDos();
    }

    @PostMapping
    public ToDo addToDo(@RequestBody ToDo toDo){
        return todoService.addToDo(toDo);
    }


    @GetMapping("{id}")
    public ToDo getById(@PathVariable String id){
        return todoService.getById(id);
    }

    @PutMapping
    public ToDo changeToDo(@PathVariable String id, @RequestBody ToDo toDo){
        return  todoService.changeToDo(id, toDo);
    }

    @DeleteMapping("{id}")
    public ToDo deleteToDo(@PathVariable String id){
        return todoService.deleteToDo(id);
    }











}
