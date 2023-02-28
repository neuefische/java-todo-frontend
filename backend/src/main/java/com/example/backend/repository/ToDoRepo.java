package com.example.backend.repository;

import com.example.backend.model.ToDoItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ToDoRepo extends MongoRepository<ToDoItem, String> {

}
