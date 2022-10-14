package de.backend;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class TaskRepo {
    List<Task> tasks = new ArrayList<>();


    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

}
