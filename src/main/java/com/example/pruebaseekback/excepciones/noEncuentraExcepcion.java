package com.example.pruebaseekback.excepciones;

public class noEncuentraExcepcion extends RuntimeException {
    public noEncuentraExcepcion(Long id) {
        super("Tarea con id No encontrada: " + id);
    }
}
