package de.backend;

import java.util.ArrayList;
import java.util.List;

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
