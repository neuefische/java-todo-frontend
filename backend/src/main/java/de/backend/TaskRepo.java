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

    public void setTask(int index, Task task) {
        tasks.set(index, task);
    }

    public Task deleteTask(Task task) {
        tasks.remove(task);
        return task;
    }
}
