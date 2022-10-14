package de.backend;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private ServiceUtils serviceUtils = new ServiceUtils();
    TaskRepo taskRepo = new TaskRepo();
    public Task addTask(TaskWithoutId taskWithoutId) {
        // Task ohne ID wird vom Frontend entgegengenommen und in einen neuen Task inklusive ID umgewandelt
        Task task = new Task(serviceUtils.generateUUID(), taskWithoutId.description(), taskWithoutId.status());
        return taskRepo.addTask(task);
    }

    public List<Task> getAllTasks() {
        return taskRepo.getAllTasks();
    }
}
