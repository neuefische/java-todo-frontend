package com.example.backend.repository;

import com.example.backend.model.ToDoItem;
import com.example.backend.service.IDService;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
@RequiredArgsConstructor
@EqualsAndHashCode
public class ToDoRepo {
    private final Map<String, ToDoItem> toDoItemMap;

    public ToDoItem addToDo(ToDoItem addedItem){ //return with or without UUID?
        toDoItemMap.put(addedItem.Id(), addedItem);
        return addedItem;
    }
    //public ToDoItem

}
