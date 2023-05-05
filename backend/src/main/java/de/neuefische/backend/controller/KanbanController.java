package de.neuefische.backend.controller;

import de.neuefische.backend.model.ToDo;
import de.neuefische.backend.service.KanbanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class KanbanController {
    private final KanbanService kanbanService;

    @GetMapping("/todo")
    public List<ToDo> getToDos() {
        return kanbanService.getToDos();
    }

    @PostMapping("/todo")
    public ToDo postToDo(@RequestBody ToDo toDo) {
        return kanbanService.postToDo(toDo.getDescription(), toDo.getStatus());
    }

    @GetMapping("/todo/{id}")
    public ToDo getToDo(@PathVariable String id) {
        return kanbanService.getToDo(id);
    }

    @PutMapping("/todo/{id}")
    public ToDo putToDo(@PathVariable String id, @RequestBody ToDo toDo){
        return kanbanService.editToDo(id, toDo);
    }

    @DeleteMapping("/todo/{id}")
    public ToDo deleteToDo(@PathVariable String id) {
        return kanbanService.deleteToDo(id);
    }
}
