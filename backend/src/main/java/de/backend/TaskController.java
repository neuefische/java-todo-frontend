package de.backend;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todo")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public List<Task> getAllTasks (){
        return taskService.getAllTasks();
    }

    @PostMapping()
    public Task addTask (@RequestBody Task task){
        return taskService.addTask(task);
    }
}
