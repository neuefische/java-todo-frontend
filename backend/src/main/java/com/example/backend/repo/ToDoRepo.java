package com.example.backend.repo;

import com.example.backend.model.ToDo;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ToDoRepo {

    private Map<String, ToDo> toDos = new HashMap<>(
            Map.of(
                    "1", new ToDo("1", "coden", "OPEN"),
                    "2", new ToDo("2", "essen", "DOING"),
                    "3", new ToDo("3", "schlafen", "DONE")));


    public List<ToDo> getAllToDos() {
        return new ArrayList<>(toDos.values());
    }

    public ToDo postNewToDo(ToDo toDo) {
        toDo.setId(Integer.toString(toDos.size() + 1));
        toDos.put(toDo.getId(), toDo);
        return toDos.get(toDo.getId());
    }

    public ToDo getToDoById(String id) {
        return toDos.get(id);
    }

    public ToDo putToDo(ToDo newToDo) {
        toDos.put(newToDo.getId(), newToDo);
        return toDos.get(newToDo.getId());
    }

    public void deleteToDoById(String id) {
        Map<String, ToDo> newToDos = new HashMap<>();
        int i = 1;
        toDos.remove(id);
        for (ToDo value : toDos.values()) {
            value.setId(Integer.toString(i));
            newToDos.put(value.getId(), value);
            i++;
        }
        toDos = newToDos;
    }
}
