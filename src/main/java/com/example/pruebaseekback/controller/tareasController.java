package com.example.pruebaseekback.controller;
import com.example.pruebaseekback.model.tareas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.pruebaseekback.service.tareaService;
import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/tareas")
public class tareasController {

    @Autowired
    private tareaService tareaService;

    @GetMapping
    public ResponseEntity<List<tareas>> getAllTasks() {
        return new ResponseEntity<>(tareaService.getAllTareas(), HttpStatus.OK);//200
    }

    @PostMapping
    public ResponseEntity<tareas> addTareas(@RequestBody tareas tareas) {
        return new ResponseEntity<>(tareaService.addTarea(tareas), HttpStatus.CREATED);//201 credo
    }

    @PutMapping("/{id}")
    public ResponseEntity<tareas> updateEstado(@PathVariable Long id, @RequestBody tareas tareas) {
        tareas updatedTask = tareaService.updateTarea(id, tareas);
        if (updatedTask != null) {
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarea(@PathVariable Long id) {
        if (tareaService.deleteTarea(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404
    }
}
