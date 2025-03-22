package org.example.retodam.service;

import org.example.retodam.model.Solicitud;
import org.example.retodam.model.Vacante;

import java.util.List;

public interface SolicitudService {

    List<Solicitud> obtenerSolicitudes();
    void addOrUpdateSolicitud(Solicitud solicitud);
    List<Solicitud> encontrarPorVacante(Vacante vacante);
}
