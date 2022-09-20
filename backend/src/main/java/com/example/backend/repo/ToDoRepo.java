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
                    "1", new ToDo("Wash the car", "OPEN", "1")));

    public List<ToDo> getAllToDos(){
        return new ArrayList<ToDo>(toDos.values());
    }

    public ToDo getById(String id) {
                return toDos.get(id);
            }

    public ToDo addNewToDo(ToDo toDo) {
        toDos.put(toDo.getId(), toDo);
        return toDo;
    }

    public ToDo changeToDo(ToDo toChangeToDo) {
        toDos.get(toChangeToDo.getId()).setStatus(toChangeToDo.getStatus());
        toDos.get(toChangeToDo.getId()).setDescription(toChangeToDo.getDescription());
        return toDos.get(toChangeToDo.getId());
    }


    public Map<String, ToDo> getToDos() {
        return toDos;
    }

    public ToDo deleteToDo(String id) {
        return toDos.remove(id);
    }
}
