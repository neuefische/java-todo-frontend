package de.neuefische.backend.service;

import de.neuefische.backend.model.TodoElement;
import de.neuefische.backend.repository.TodoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepo repo;
    public List<TodoElement> getAllTodo() {
        return repo.getAllTodo();
    }
}
