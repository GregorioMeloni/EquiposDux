package com.dux.pruebatecnica.data.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dux.pruebatecnica.data.models.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    List<Equipo> findByNombreContainingOrLigaEqualsOrPaisEquals(String nombre, String liga, String pais);
}