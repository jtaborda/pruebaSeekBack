package com.example.pruebaseekback.repository;

import com.example.pruebaseekback.model.tareas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface tareaRepository extends JpaRepository<tareas, String> {}