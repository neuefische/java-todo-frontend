package de.neuefische.backend.repo;

import de.neuefische.backend.model.ToDo;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToDoRepoTest {

    ToDoRepo toDoRepo = new ToDoRepo();
    ToDo test01 = new ToDo("Test1", "OPEN", "001");
    ToDo test02 = new ToDo("Test2", "OPEN", "002");
    ToDo test03 = new ToDo("Test3", "OPEN", "003");

    @Test
    void getToDos_returnListOfGivenContent() {
        toDoRepo.postToDo("Test1", "OPEN", "001");
        toDoRepo.postToDo("Test2", "OPEN", "002");
        List<ToDo> actual = toDoRepo.getToDos();
        assertEquals(List.of(test01, test02), actual);
    }

    @Test
    void postToDo_returnGivenContent() {
        ToDo actual = toDoRepo.postToDo("Test1", "OPEN", "001");
        assertEquals(test01, actual);
    }

    @Test
    void getToDoById_returnContentByGivenId() {
        assertEquals(toDoRepo.postToDo("Test1", "OPEN", "001"), toDoRepo.getToDoById("001"));
    }

    @Test
    void getToDoById_StatusExceptionNotFound_ifGivenIdDoesNotExists() {
        try {
            toDoRepo.postToDo("Test1", "OPEN", "001");
        } catch (ResponseStatusException ex) {
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        }

    }

    @Test
    void editToDo_returnEditedContentWithSameId() {
        toDoRepo.postToDo("Test1", "OPEN", "001");
        ToDo test03 = new ToDo("Test3", "OPEN", "001");
        assertEquals(test03, toDoRepo.editToDo("001", test03));
    }

    @Test
    void deleteToDo_returnAllContentButRemovedId() {
        toDoRepo.postToDo("Test1", "OPEN", "001");
        toDoRepo.postToDo("Test2", "OPEN", "002");
        toDoRepo.postToDo("Test3", "OPEN", "003");
        toDoRepo.deleteToDo("002");

        List<ToDo> actual = toDoRepo.getToDos();
        assertEquals(List.of(test01, test03), actual);
    }

    @Test
    void deleteToDo_returnStatusExceptionNotFound_ifGivenIdDoesNotExists() {
        toDoRepo.postToDo("Test1", "OPEN", "001");
        toDoRepo.postToDo("Test2", "OPEN", "002");
        toDoRepo.postToDo("Test3", "OPEN", "003");
        try {
            toDoRepo.deleteToDo("005");
        } catch (ResponseStatusException ex) {
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        }
    }
}