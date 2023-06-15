package com.example.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepo toDoRepo;
    private final UuidService uuidService;

    public ToDo addTodo(ToDo newTodo) {
        String id = uuidService.getRandomId();
        newTodo.setId(id);
        return toDoRepo.addTodo(newTodo);
    }

    public List<ToDo> getAllTodos() {
        return toDoRepo.getAllTodos();
    }

    public ToDo getTodoById(String id) {
        return toDoRepo.getTodoById(id);
    }

    public ToDo updateTodoById(String id, ToDo todo) {
        ToDo todoToUpdate = toDoRepo.getTodoById(id);
        if (todoToUpdate == null) {
            throw new NoSuchElementException("No Element with this ID");
        }
        todoToUpdate.setDescription(todo.getDescription());
        todoToUpdate.setStatus(todo.getStatus());

        return todoToUpdate;
    }

    public void deleteById(String id) {
        ToDo toDo = toDoRepo.getTodoById(id);
        if (toDo == null) {
            throw new NoSuchElementException("No Element with this ID");
        }
        toDoRepo.delete(toDo);
    }
}
