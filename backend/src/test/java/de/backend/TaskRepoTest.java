package de.backend;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskRepoTest {
    TaskRepo taskRepo = new TaskRepo();

    @Test
    void addTestTaskToTaskRepoReturnTask() {
        // GIVEN
        Task task = new Task("TestPost", TaskStatus.TODO);

        //WHEN
        Task actual = taskRepo.addTask(task);
        Task expected = task;

        // THEN
        assertEquals(expected, actual);

    }
}