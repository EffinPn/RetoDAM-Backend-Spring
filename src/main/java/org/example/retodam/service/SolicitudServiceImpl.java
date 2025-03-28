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

    @Override
    public List<Solicitud> obtenerSolicitudes() {
        return solicitudRepository.findAll();
    }

    @Override
    public void addOrUpdateSolicitud(Solicitud solicitud) {
        solicitudRepository.save(solicitud);
    }

    @Override
    public List<Solicitud> encontrarPorVacante(Vacante vacante) {
        return solicitudRepository.findByVacante(vacante);
    }

}
