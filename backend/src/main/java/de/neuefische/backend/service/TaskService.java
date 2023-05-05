package de.neuefische.backend.service;

import de.neuefische.backend.model.Task;
import de.neuefische.backend.repo.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;

    public List<Task> getAllTasks() {
        return taskRepo.getAllTasks();
    }

    public Task addTask(Task task) {
        return taskRepo.addTask(task.withId(UUID.randomUUID().toString()));
    }

    public Task getTaskById(String id) {
        return taskRepo.getTaskById(id);
    }
}
