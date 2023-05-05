package de.neuefische.backend.repo;

import de.neuefische.backend.model.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TaskRepo {

    private final Map<String,Task> taskMap = new HashMap<>();

    public List<Task> getAllTasks() {
        return new ArrayList<>(taskMap.values());
    }

    public Task addTask(Task task) {
        taskMap.put(task.getId(), task);
        return task;
    }

    public Task getTaskById(String id) {
        return taskMap.get(id);
    }
}
