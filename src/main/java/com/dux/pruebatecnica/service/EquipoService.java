
package com.dux.pruebatecnica.service;

import com.dux.pruebatecnica.data.models.Equipo;
import com.dux.pruebatecnica.data.repository.EquipoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EquipoService {
    private final EquipoRepository equipoRepository;
    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }
    public List<Equipo> obtenerTodosLosEquipos() {
        return equipoRepository.findAll();
    }
    public Equipo obtenerEquipoPorId(Long id) {
        return equipoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipo no encontrado" ));
    }
    public List<Equipo> buscarEquiposPorNombreLigaOPais(String valor) {
        return equipoRepository.findByNombreContainingOrLigaEqualsOrPaisEquals(valor, valor, valor);
    }

    public Equipo guardarEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public Equipo actualizarEquipo(Long id, Equipo equipoActualizado) {
        Optional<Equipo> optionalEquipo = equipoRepository.findById(id);
        if (optionalEquipo.isPresent()) {
            Equipo equipo = optionalEquipo.get();
            equipo.setNombre(equipoActualizado.getNombre());
            equipo.setLiga(equipoActualizado.getLiga());
            equipo.setPais(equipoActualizado.getPais());
            return equipoRepository.save(equipo);
        } else {
            String mensajeError = "Equipo no encontrado";
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, mensajeError);
        }
    }
    public void eliminarEquipo(Long id) {
        Optional<Equipo> optionalEquipo = equipoRepository.findById(id);
        if (optionalEquipo.isPresent()) {
            equipoRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El equipo con el ID " + id + " no existe");
        }
    }

}
