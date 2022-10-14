package de.backend;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskRepoTest {
    TaskRepo taskRepo = new TaskRepo();

    @Test
    void addTestTaskToTaskRepoReturnTask() {
        // GIVEN
        Task task = new Task("TestPost", TaskStatus.OPEN);

        //WHEN
        Task actual = taskRepo.addTask(task);
        Task expected = task;

        // THEN
        assertEquals(expected, actual);

    }

    @Test
    void getAllTasksReturnsEmptyList(){
        //GIVEN
        //WHEN
        List<Task> actual = taskRepo.getAllTasks();
        //THEN
        List<Task> expected = new ArrayList<>();
        assertEquals(expected,actual);
    }
}