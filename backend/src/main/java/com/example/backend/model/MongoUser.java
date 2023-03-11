package com.example.backend.model;
//@Document Todo
public record MongoUser(String password, String username, String id, String role) {
}
