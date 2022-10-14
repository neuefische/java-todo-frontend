package de.backend;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

// Für Unit Test testen wir nur die Klasse

// von TaskService aufgerufene Klassen werden gemockt,
// wenn jemand in anderen Klassen arbeitet, kann man testen und es verändert nicht das Ergebnis
class TaskServiceTest {

    TaskService taskService = new TaskService();
    TaskRepo taskRepo = mock(TaskRepo.class);


    @Test
    void addTestToDoPostToTaskReturnTask() {
        //GIVEN
        Task testPostTask = new Task("TestPost", TaskStatus.OPEN);
        when(taskRepo.addTask(testPostTask)).thenReturn(testPostTask);

        //WHEN
        Task actual = taskService.addTask(testPostTask);
        //THEN
        Task expected = testPostTask;
        assertEquals(expected, actual);
    }

    @Test
    void getAllTasksReturnsEmptyList(){
        //GIVEN
        when(taskRepo.getAllTasks()).thenReturn(new ArrayList<>());
        //WHEN
        List<Task> actual = taskService.getAllTasks();
        //THEN
        List<Task> expected = new ArrayList<>();
        assertEquals(expected,actual);
    }
}