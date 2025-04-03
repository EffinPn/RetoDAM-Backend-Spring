package org.example.retodam.controller;

import org.example.retodam.dto.SolicitudDTO;
import org.example.retodam.model.Solicitud;
import org.example.retodam.model.Usuario;
import org.example.retodam.model.Vacante;
import org.example.retodam.service.SolicitudService;
import org.example.retodam.service.UsuarioService;
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

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    VacanteService vacanteService;


    // Mandar solicitud ANDROID
    @PostMapping("/solicitar")
    public ResponseEntity<String> mandarSolicitud(@RequestBody SolicitudDTO solicitudDTO){
        try {
            // Llamar al service para mandar la solicitud
            solicitudService.mandarSolicitud(solicitudDTO);
            return new ResponseEntity<>("Solicitud enviada correctamente", HttpStatus.OK);
        } catch (RuntimeException e) {
            // En caso de error (usuario o vacante no encontrados)
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    // Consultar solicitudes por usuario ANDROID
    @GetMapping("/getByUsuario")
    public ResponseEntity<List<SolicitudDTO>> getSolicitudesUsuario(@RequestParam String username){

        List<Solicitud> solicitudes = solicitudService.getSolicitudesUsuario(username);

        if (!solicitudes.isEmpty()) {
            List<SolicitudDTO> solicitudesDTO = solicitudService.solicitudesToDTO(solicitudes);

            return new ResponseEntity<>(solicitudesDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar solicitudes por ID ANDROID
    @DeleteMapping("/cancelarSolicitud")
    public ResponseEntity<String> eliminarSolicitud(@RequestParam int id){
        if(solicitudService.eliminarSolicitud(id)){
            return new ResponseEntity<>("Solicitud eliminada correctamente", HttpStatus.OK);
        } else{
            return new ResponseEntity<>("No se ha encontrado la solicitud", HttpStatus.NOT_FOUND);
        }
    }
}
