package com.example.pruebaseekback.service;
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

    public tareas updateEstado(String id, String status) {
        Optional<tareas> tareas = tareaRepository.findById(id);
        if (tareas.isPresent()) {
            tareas t = tareas.get();
            t.setEstado(status);
            return tareaRepository.save(t);
        }
        return null;
    }

    public void deleteTarea(String id) {
        tareaRepository.deleteById(id);
    }
}
