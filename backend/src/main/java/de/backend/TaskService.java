package de.backend;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {

    private ServiceUtils serviceUtils;
    private TaskRepo taskRepo;


    public TaskService(ServiceUtils serviceUtils, TaskRepo taskRepo) {
        this.serviceUtils = serviceUtils;
        this.taskRepo = taskRepo;
    }

    public Task addTask(TaskWithoutId taskWithoutId) {
        // Task ohne ID wird vom Frontend entgegengenommen und in einen neuen Task inklusive ID umgewandelt
        Task task = new Task(serviceUtils.generateUUID(), taskWithoutId.description(), taskWithoutId.status());
        return taskRepo.addTask(task);
    }

    public List<Task> getAllTasks() {
        return taskRepo.getAllTasks();
    }

    public Task getTaskById(String id) {
        List<Task> tasks = taskRepo.getAllTasks();
        for (Task task : tasks ) {
           if(task.id().equals(id)){
               return task;
           }
        }
        throw new NoSuchElementException("No task with id: " + id + "was found");
    }

    public Task updateTaskById(String id, Task task) {
        List<Task> tasks = taskRepo.getAllTasks();
        for (Task item : tasks) {
            if(item.id().equals(id)){
                System.out.println("Item "+ item.id() + " TAsks index Of " + tasks.indexOf(item) );
                taskRepo.removeTask(tasks.indexOf(item));
            }
        }
        return taskRepo.addTask(task);
    }
}
