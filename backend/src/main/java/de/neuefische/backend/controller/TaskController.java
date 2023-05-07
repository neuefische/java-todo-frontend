package de.neuefische.backend.controller;

import de.neuefische.backend.model.Task;
import de.neuefische.backend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("todo")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("todo/{id}")
    public Task getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("todo")
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @PutMapping("todo/{id}")
    public Task updateTask(@PathVariable String id,@RequestBody Task task) {
        return taskService.updateTask(task,id);
    }

    @DeleteMapping("todo/{id}")
    public Task deleteTask(@PathVariable String id) {
        return taskService.deleteTask(id);
    }
}
