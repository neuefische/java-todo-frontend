package de.neuefische.backend.service;

import de.neuefische.backend.model.Task;
import de.neuefische.backend.model.TaskStatus;
import de.neuefische.backend.repo.TaskRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    private final IdService idService = mock(IdService.class);
    private final TaskRepo taskRepo = mock(TaskRepo.class);

    private final TaskService taskService = new TaskService(taskRepo,idService);

    @Test
    void whenGetAllTasks_thenReturnInitialEmptyList(){

        when(taskRepo.getAllTasks()).thenReturn(new ArrayList<>());
        List<Task> actual = taskService.getAllTasks();

        verify(taskRepo).getAllTasks();
        assertEquals(new ArrayList<>(),actual);
    }

    @Test
    void whenGetTaskWithValidId_thenReturnTask(){
        //GIVEN
        Task task = new Task("1","test", TaskStatus.OPEN);
        when(taskRepo.getTaskById("1")).thenReturn(task);

        //WHEN
        Task actual = taskService.getTaskById("1");

        //THEN
        verify(taskRepo).getTaskById("1");
        assertEquals(task,actual);
    }

    @Test
    void whenAddingNewTask_returnCreatedTask(){
        //GIVEN
        Task task = new Task("1","test", TaskStatus.OPEN);
        when(idService.generateId()).thenReturn("1");
        when(taskRepo.addTask(task.withId("1"))).thenReturn(task);

        //WHEN
        Task actual = taskService.addTask(task);

        //THEN
        verify(idService).generateId();
        verify(taskRepo).addTask(task.withId("1"));
        assertEquals(task,actual);
    }

    @Test
    void whenUpdatingTask_returnUpdatedTask(){
        Task oldTask = new Task("1","test", TaskStatus.OPEN);
        taskRepo.addTask(oldTask);
        Task updatedTask = new Task("1","test", TaskStatus.IN_PROGRESS);
        when(taskRepo.updateTask(updatedTask,oldTask.getId())).thenReturn(updatedTask);

        Task actual = taskService.updateTask(updatedTask,"1");

        verify(taskRepo).updateTask(updatedTask,"1");
        assertEquals(updatedTask,actual);
    }

    @Test
    void whenUpdatingTaskWithInvalidId_throwException(){
        when(taskRepo.updateTask(new Task("1","test", TaskStatus.OPEN),"2")).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () -> taskService.updateTask(new Task("1","test", TaskStatus.OPEN),"2"));
    }

    @Test
    void whenDeletingTaskWithValidId_thenReturnDeletedTask(){
        Task task = new Task("1","test", TaskStatus.OPEN);
        when(taskRepo.deleteTask("1")).thenReturn(task);

        Task actual = taskService.deleteTask("1");

        verify(taskRepo).deleteTask("1");
        assertEquals(task,actual);
    }

    @Test
    void whenDeletingTaskWithInvalidId_throwException(){
        when(taskRepo.deleteTask("2")).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () -> taskService.deleteTask("2"));
    }

}