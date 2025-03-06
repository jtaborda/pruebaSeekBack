package com.example.pruebaseekback;

import com.example.pruebaseekback.model.tareas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.pruebaseekback.repository.tareaRepository;
import com.example.pruebaseekback.service.tareaService;
import com.example.pruebaseekback.excepciones.noEncuentraExcepcion;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class PruebaSeekBackApplicationTests {

    @Test
    void contextLoads() {
    }

    @Mock
    private tareaRepository tareaRepository;

    @InjectMocks
    private tareaService tareaService;

    @Test
    public void testCrearTarea() {
        tareas tareas = new tareas("","","");
        tareas.setTitulo("Probando la tareaa");
        tareas.setDescripcion("Describiendo prueba de tarea");
        tareas.setEstado("activo");

        Mockito.when(tareaRepository.save(tareas)).thenReturn(tareas);

        tareas creaTarea = tareaService.addTarea(tareas);

        Assertions.assertNotNull(creaTarea);
    }

    @Test
    public void tareaIdNoEncontrada() {
        // Cuando no existe una tarea con ese ID, lanzamos la excepción
        String idTarea = "100";
        Mockito.when(tareaRepository.findById(idTarea)).thenReturn(java.util.Optional.empty());

        noEncuentraExcepcion exception = assertThrows(noEncuentraExcepcion.class, () -> {
            tareaService.getXId(idTarea);
        });

        Assertions.assertEquals("Tarea con id No encontrada: " + idTarea, exception.getMessage());
    }

    @Test
    public void actualizarTarea() {
        String idTarea = "1";
        tareas tareaExistente = new tareas("Tarea 1", "Descripción tarea 1", "activa");

        Mockito.when(tareaRepository.findById(idTarea)).thenReturn(Optional.of(tareaExistente));

        tareas nuevaTarea = new tareas("Tarea actualizada", "Descripción actualizada", "pendiente");

        Mockito.when(tareaRepository.save(Mockito.any(tareas.class))).thenReturn(nuevaTarea);

        tareas tareaActualizada = tareaService.updateTarea(idTarea, nuevaTarea);


        assertEquals("Tarea actualizada", tareaActualizada.getTitulo());
        assertEquals("Descripción actualizada", tareaActualizada.getDescripcion());
        assertEquals("pendiente", tareaActualizada.getEstado());
    }

    @Test
    public void eliminarTarea() {
        String idTarea = "2";
        tareas tareaExistente = new tareas("Tarea a eliminar", "Descripción tarea", "En progreso");

        Mockito.when(tareaRepository.findById(idTarea)).thenReturn(Optional.of(tareaExistente));
        tareaService.deleteTarea(idTarea);

        Mockito.verify(tareaRepository, Mockito.times(1)).delete(tareaExistente);
    }

}
