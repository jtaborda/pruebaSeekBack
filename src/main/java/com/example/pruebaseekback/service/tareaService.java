package com.example.pruebaseekback.service;
import com.example.pruebaseekback.excepciones.noEncuentraExcepcion;
import com.example.pruebaseekback.model.tareas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.example.pruebaseekback.repository.tareaRepository;
@Service
public class tareaService {
    @Autowired
    private tareaRepository tareaRepository;

    public List<tareas> getAllTareas() {
        return tareaRepository.findAll();
    }

    public tareas addTarea(tareas tareas) {
        return tareaRepository.save(tareas);
    }

    public tareas updateTarea(String id, tareas  nuevaTarea) {
        Long number = Long.parseLong(id);
        Optional<tareas> tareaExistente = tareaRepository.findById(id);
        if (!tareaExistente.isPresent()) {
            throw new noEncuentraExcepcion(number);
        }
        tareas tarea = tareaExistente.get();
        tarea.setTitulo(nuevaTarea.getTitulo());
        tarea.setDescripcion(nuevaTarea.getDescripcion());
        tarea.setEstado(nuevaTarea.getEstado());
        return tareaRepository.save(tarea);
    }

    public tareas getXId(String id) {
        Long number = Long.parseLong(id);
        Optional<tareas> tareas = tareaRepository.findById(id);
        if (!tareas.isPresent()) {
            throw new noEncuentraExcepcion(number);
        }

        return tareas.get();
    }

    public boolean deleteTarea(String id) {
        tareas task = getXId(id);
        if (task != null) {
            tareaRepository.delete(task);
            return true;
        }
        return false;
    }
}
