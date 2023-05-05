package de.neuefische.backend.repo;

import de.neuefische.backend.model.ToDo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ToDoRepo {
    private  Map<String, ToDo> toDoMap = new HashMap<>();




    public List<ToDo> getToDos() {
        return new ArrayList<>(toDoMap.values());
    }

    public ToDo postToDo(String description, String status, String uuid) {
        return toDoMap.put(uuid, new ToDo(description, status, uuid));
    }

    public ToDo getToDoById(String id) {
        return toDoMap.get(id);
    }

    public ToDo editToDo(String id, ToDo toDo) {
        toDoMap.replace(id, toDo);
        return toDoMap.get(id);
    }

    public ToDo deleteToDo(String id) {
        toDoMap.remove(id);
        return toDoMap.get(id);
    }
}
