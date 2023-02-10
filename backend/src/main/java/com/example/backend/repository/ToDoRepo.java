package com.example.backend.repository;

import com.example.backend.model.ToDoItem;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
@RequiredArgsConstructor
@EqualsAndHashCode
public class ToDoRepo {
    private final Map<String, ToDoItem> toDoItemMap;

    public ToDoItem addToDo(ToDoItem addedItem){
        toDoItemMap.put(addedItem.id(), addedItem);
        return addedItem;
    }
    public ToDoItem[] getAllToDoItems(){
        return toDoItemMap.values().toArray(new ToDoItem[0]);
    }
    public ToDoItem getToDoById(String id) {
        return toDoItemMap.get(id);
    }
    public ToDoItem deleteToDoById(String id) {
        return toDoItemMap.remove(id);
    }
}
