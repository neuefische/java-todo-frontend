package de.neuefische.backend.repo;

import de.neuefische.backend.model.ToDo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

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
        toDoMap.put(uuid, new ToDo(description, status, uuid));
        return toDoMap.get(uuid);
    }

    public ToDo getToDoById(String id) {
        if(toDoMap.containsKey(id)) {
            return toDoMap.get(id);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public ToDo editToDo(String id, ToDo toDo) {
        if (toDoMap.containsKey(id)) {
            toDo.setId(id);
            toDoMap.replace(id, toDo);
            return toDoMap.get(id);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such Element");
        }

    }

    public ToDo deleteToDo(String id) {
        ToDo deletedToDo = getToDoById(id);
        toDoMap.remove(id);
        return deletedToDo;
    }
}
