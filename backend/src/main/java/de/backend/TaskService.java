package de.backend;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    TaskRepo taskRepo = new TaskRepo();
    public Task addTask(Task task) {
        return taskRepo.addTask(task);
    }

    public List<Task> getAllTasks() {
        return taskRepo.getAllTasks();
    }
}
