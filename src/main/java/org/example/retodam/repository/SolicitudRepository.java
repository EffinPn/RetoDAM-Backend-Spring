package org.example.retodam.repository;

import org.example.retodam.model.Solicitud;
import org.example.retodam.model.Usuario;
import org.example.retodam.model.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
    List<Solicitud> findByVacante(Vacante vacante);

}
