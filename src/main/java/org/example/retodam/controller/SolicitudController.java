package org.example.retodam.controller;

import org.example.retodam.model.Solicitud;
import org.example.retodam.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
