package de.neuefische.backend.service;

import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.repo.ToDoRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
@Service
@RequiredArgsConstructor
public class KanbanService {
    private final ToDoRepo toDoRepo;
    private final GenerateUUID generateUUID;



    public List<ToDo> getToDos() {
        return toDoRepo.getToDos();
    }

    public ToDo postToDo(String description, String status) {
        return toDoRepo.postToDo(description, status, generateUUID.generateUUID());
    }

    public ToDo getToDo(String id) {
        return toDoRepo.getToDoById(id);
    }

    public ToDo editToDo(String id, ToDo toDo) {
        return toDoRepo.editToDo(id, toDo);
    }

    public ToDo deleteToDo(String id) {
        return toDoRepo.deleteToDo(id);
    }
}
