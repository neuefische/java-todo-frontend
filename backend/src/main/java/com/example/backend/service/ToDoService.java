package com.example.backend.service;


import com.example.backend.model.ToDo;
import com.example.backend.repo.ToDoRepo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepo toDoRepo;

    public List<ToDo> getAllToDos() {
        return toDoRepo.getAllToDos();
    }

    public ToDo postNewToDo(ToDo toDo) {
        return toDoRepo.postNewToDo(toDo);
    }

    public ToDo getToDoById(String id) {
        if (toDoRepo.getToDoById(id) == null)
            throw new NoSuchElementException();
        return toDoRepo.getToDoById(id);
    }

    public ToDo putToDo(ToDo newToDo) {
        return toDoRepo.putToDo(newToDo);
    }

    public void deleteToDoById(String id) {
        if (toDoRepo.getToDoById(id) == null)
            throw new NoSuchElementException();
        toDoRepo.deleteToDoById(id);
    }
}
