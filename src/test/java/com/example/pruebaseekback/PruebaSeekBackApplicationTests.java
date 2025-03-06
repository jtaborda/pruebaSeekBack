package com.example.pruebaseekback;

import com.example.pruebaseekback.model.tareas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.pruebaseekback.repository.tareaRepository;
import com.example.pruebaseekback.service.tareaService;
import com.example.pruebaseekback.excepciones.noEncuentraExcepcion;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
        tareas tareas = new tareas(null,"","","");
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
        Long idTarea = 100L;
        Mockito.when(tareaRepository.findById(idTarea)).thenReturn(java.util.Optional.empty());

        noEncuentraExcepcion exception = assertThrows(noEncuentraExcepcion.class, () -> {
            tareaService.getXId(idTarea);
        });

        Assertions.assertEquals("Tarea con id No encontrada: " + idTarea, exception.getMessage());
    }

    @Test
    public void actualizarTarea() {
        Long idTarea=1L;
        tareas tareaExistente = new tareas(null,"Tarea 1", "Descripción tarea 1", "activa");

        Mockito.when(tareaRepository.findById(idTarea)).thenReturn(Optional.of(tareaExistente));

        tareas nuevaTarea = new tareas(null,"Tarea actualizada", "Descripción actualizada", "pendiente");

        Mockito.when(tareaRepository.save(Mockito.any(tareas.class))).thenReturn(nuevaTarea);

        tareas tareaActualizada = tareaService.updateTarea(idTarea, nuevaTarea);


        assertEquals("Tarea actualizada", tareaActualizada.getTitulo());
        assertEquals("Descripción actualizada", tareaActualizada.getDescripcion());
        assertEquals("pendiente", tareaActualizada.getEstado());
    }

    @Test
    public void eliminarTarea() {
        Long idTarea = 2L;
        tareas tareaExistente = new tareas(null,"Tarea a eliminar", "Descripción tarea", "En progreso");

        Mockito.when(tareaRepository.findById(idTarea)).thenReturn(Optional.of(tareaExistente));
        tareaService.deleteTarea(idTarea);

        Mockito.verify(tareaRepository, Mockito.times(1)).delete(tareaExistente);
    }

}
