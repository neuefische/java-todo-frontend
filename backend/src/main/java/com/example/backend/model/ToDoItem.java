package com.example.backend.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("todos")
public record ToDoItem(String description, Status status, String id, String userName) {

}
