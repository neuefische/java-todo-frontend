package de.backend;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    TaskRepo taskRepo = new TaskRepo();
    public Task addTask(Task task) {
        return taskRepo.addTask(task);
    }
}
