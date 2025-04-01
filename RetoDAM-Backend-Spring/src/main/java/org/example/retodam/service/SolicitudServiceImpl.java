package org.example.retodam.service;

import org.example.retodam.model.Solicitud;
import org.example.retodam.model.Vacante;
import org.example.retodam.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    @Autowired
    SolicitudRepository solicitudRepository;


    // Añadir solicitud ANDROID
    @Override
    public Solicitud mandarSolicitud(Solicitud solicitud) {
        return solicitudRepository.save(solicitud);
    }

    // Consultar solicitudes por usuario ANDROID
    @Override
    public List<Solicitud> getSolicitudesUsuario(String username) {
        return solicitudRepository.getSolicitudesUsuario(username);
    }

    // Eliminar solicitud por ID ANDROID
    @Override
    public boolean eliminarSolicitud(int id){
        if (solicitudRepository.existsById(id)) {
            solicitudRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
