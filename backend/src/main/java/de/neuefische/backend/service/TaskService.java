package de.neuefische.backend.service;

import de.neuefische.backend.exceptions.TaskNotFoundException;
import de.neuefische.backend.model.Task;
import de.neuefische.backend.repo.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;
    private final IdService idService;

    public List<Task> getAllTasks() {
        return taskRepo.getAllTasks();
    }

    public Task addTask(Task task) {
        return taskRepo.addTask(task.withId(idService.generateId()));
    }

    public Task getTaskById(String id) {
        return taskRepo.getTaskById(id);
    }

    public Task updateTask(Task task, String id) {
        return taskRepo.updateTask(task, id);
    }

    public Task deleteTask(String id) {
        return taskRepo.deleteTask(id);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handleTaskNotFoundException(TaskNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
