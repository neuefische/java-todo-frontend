package com.example.backend.service;


import com.example.backend.model.ToDo;
import com.example.backend.repo.ToDoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class ToDoService {

    private ToDoRepo toDoRepo;


    @Autowired
    public ToDoService(ToDoRepo toDoRepo) {
        this.toDoRepo = toDoRepo;
    }

    public List<ToDo> getAllToDos(){
        return toDoRepo.getAllToDos();
    }

    public ToDo addToDo (ToDo toDo){
        Map<String ,ToDo> presentToDos = toDoRepo.getToDos();
            int newId1 = presentToDos.size()+1;
            String newId = String.valueOf(newId1);
            toDo.setId(newId);
            return toDoRepo.addNewToDo(toDo);

    }
    public ToDo getById(String id){
        ToDo foundToDo = toDoRepo.getById(id);
        if(foundToDo == null){
            throw new NoSuchElementException("No ToDo was found with id: " + id);
        }
        return toDoRepo.getById(id);
    }

    public ToDo changeToDo(String id, ToDo toDoDetails) {
        Map<String, ToDo> presentToDos = toDoRepo.getToDos();
        ToDo changeToDo = presentToDos.get(id);

        changeToDo.setStatus(toDoDetails.getStatus());
        changeToDo.setDescription(toDoDetails.getDescription());
        return toDoRepo.changeToDo(changeToDo);
    }

    public ToDo deleteToDo(String id) {
        return toDoRepo.deleteToDo(id);
    }
}
