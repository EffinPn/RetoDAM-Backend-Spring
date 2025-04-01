package org.example.retodam.controller;

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
    public ResponseEntity<String> mandarSolicitud(@RequestBody Solicitud solicitud){
        // Verificar si el usuario existe en la base de datos
        Usuario usuario = usuarioService.findByUsername(solicitud.getUsuario().getUsername());
        if (usuario == null) {
            return new ResponseEntity<>("No existe el usuario en la BBDD", HttpStatus.NOT_FOUND);
        }

        // Verificar si la vacante existe en la base de datos
        Vacante vacante = vacanteService.getById(solicitud.getVacante().getId_vacante());
        if (vacante == null) {
            return new ResponseEntity<>("No existe la vacante en la BBDD", HttpStatus.NOT_FOUND);
        }

        // Si el usuario y la vacante existen, los asignamos a la solicitud
        solicitud.setUsuario(usuario);
        solicitud.setVacante(vacante);

        // Guardar la solicitud en la base de datos
        solicitudService.mandarSolicitud(solicitud);

        return new ResponseEntity<>("Solicitud enviada correctamente", HttpStatus.OK);
    }


    // Consultar solicitudes por usuario ANDROID
    @GetMapping("/getByUsuario")
    public ResponseEntity<List<Solicitud>> getSolicitudesUsuario(@RequestParam String username){

        List<Solicitud> solicitudes = solicitudService.getSolicitudesUsuario(username);

        if (!solicitudes.isEmpty()) {
            return new ResponseEntity<>(solicitudes, HttpStatus.OK);
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
