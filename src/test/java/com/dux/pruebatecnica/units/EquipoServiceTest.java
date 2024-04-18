package com.dux.pruebatecnica.units;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import com.dux.pruebatecnica.data.models.Equipo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.dux.pruebatecnica.data.repository.EquipoRepository;
import com.dux.pruebatecnica.service.EquipoService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EquipoServiceTest {

    @Mock
    private EquipoRepository equipoRepository;

    @InjectMocks
    private EquipoService equipoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testObtenerTodosLosEquipos() {
        Equipo equipo1 = new Equipo();
        Equipo equipo2 = new Equipo();
        when(equipoRepository.findAll()).thenReturn(Arrays.asList(equipo1, equipo2));

        List<Equipo> equipos = equipoService.obtenerTodosLosEquipos();
        assertEquals(2, equipos.size());
    }
    @Test
    public void testObtenerEquipoPorIdExistente() {
        long id = 1L;
        Equipo equipo = new Equipo();
        equipo.setId(id);
        when(equipoRepository.findById(id)).thenReturn(Optional.of(equipo));

        Equipo resultado = equipoService.obtenerEquipoPorId(id);
        assertEquals(id, resultado.getId());
    }

    /*@Test
    public void testObtenerEquipoPorIdNoExistente() {
        long id = 1L;
        when(equipoRepository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            equipoService.obtenerEquipoPorId(id);
        });

        assertNull(exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getStatusCode().value());
    }*/
    @Test
    public void testObtenerEquipoPorIdNoExistente() {
        long id = 1L;
        when(equipoRepository.findById(id)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            equipoService.obtenerEquipoPorId(id);
        });

        assertEquals("Equipo no encontrado", exception.getReason());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}
