package org.example.retodam.service;

import org.example.retodam.model.Solicitud;
import org.example.retodam.model.Vacante;

import java.util.List;

public interface SolicitudService {

    // Añadir solicitud ANDROID
    Solicitud mandarSolicitud(Solicitud solicitud);

    // Consultar solicitudes por usuario ANDROID
    List<Solicitud> getSolicitudesUsuario(String username);

    // Eliminar solicitud ANDROID
    boolean eliminarSolicitud(int id);
}
