package org.example.retodam.controller;

import org.example.retodam.model.Solicitud;
import org.example.retodam.model.Vacante;
import org.example.retodam.service.SolicitudService;
import org.example.retodam.service.VacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("solicitudes")
public class SolicitudController {

    @Autowired
    SolicitudService solicitudService;

    @GetMapping("/getall")
    public ResponseEntity<List<Solicitud>> getAll() {
        return new ResponseEntity<>(solicitudService.obtenerSolicitudes(), HttpStatus.OK);
    }

    @PostMapping("/addorupdate")
    public String addorupdate(@RequestBody Solicitud solicitud) {
        solicitudService.addOrUpdateSolicitud(solicitud);
        return "Solicitud agregada/actualizada";
    }

    @GetMapping("/getbyvacante")
    public ResponseEntity<List<Solicitud>> getByVacante(Vacante vacante) {

        List<Solicitud> solicitudes = solicitudService.encontrarPorVacante(vacante);
        if (!solicitudes.isEmpty()) {
            return new ResponseEntity<>(solicitudes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
