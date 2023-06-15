package com.example.backend;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ToDoRepo {

    List<ToDo> listOfAllTodos = new ArrayList<>();

    public ToDo addTodo(ToDo newTodo) {
        listOfAllTodos.add(newTodo);
        return newTodo;
    }

    public List<ToDo> getAllTodos() {
        return this.listOfAllTodos;
    }

    public ToDo getTodoById(String id) {
        for (ToDo todo : listOfAllTodos) {
            if (todo.getId().equals(id)) {
                return todo;
            }
        }
         throw new NoSuchElementException("No Element with this ID");
    }

}
