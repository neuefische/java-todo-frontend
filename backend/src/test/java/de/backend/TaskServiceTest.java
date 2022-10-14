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
    ServiceUtils serviceUtils = mock(ServiceUtils.class);


    @Test
    void addTestToDoPostToTaskReturnTask() {
        //GIVEN
        TaskWithoutId testTaskWithoutId = new TaskWithoutId("TestPost", TaskStatus.OPEN);
        Task testTask = new Task("1", testTaskWithoutId.description(),testTaskWithoutId.status());

        when(serviceUtils.generateUUID()).thenReturn("1");
        when(taskRepo.addTask(testTask)).thenReturn(testTask);

        //WHEN
        Task actual = taskService.addTask(testTaskWithoutId);
        //THEN
        Task expected = testTask;
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