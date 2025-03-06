package com.example.pruebaseekback.controller;
import com.example.pruebaseekback.model.tareas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.pruebaseekback.service.tareaService;
import java.util.List;
@RestController
@RequestMapping("/api/tareas")
public class tareasController {

        @Autowired
        private tareaService tareaService;

        @GetMapping
        public List<tareas> getTasks() {
            return tareaService.getAllTareas();
        }

        @PostMapping
        public tareas addTask(@RequestBody tareas tareas) {
            return tareaService.addTarea(tareas);
        }

        @PutMapping("/{id}")
        public tareas updateEstado(@PathVariable String id, @RequestBody tareas tareas) {
            return tareaService.updateEstado(id, tareas.getEstado());
        }

        @DeleteMapping("/{id}")
        public void deleteTarea(@PathVariable String id) {
            tareaService.deleteTarea(id);
        }
    }
