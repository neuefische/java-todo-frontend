package de.neuefische.backend.exceptions;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(String id) {
        super("Todo not found with id : " + id + "!");
    }

}
