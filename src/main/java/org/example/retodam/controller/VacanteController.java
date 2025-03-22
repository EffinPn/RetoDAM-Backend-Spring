package org.example.retodam.controller;

import org.example.retodam.model.Solicitud;
import org.example.retodam.model.Vacante;
import org.example.retodam.service.VacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("vacantes")
public class VacanteController {

    @Autowired
    VacanteService vacanteService;

    @GetMapping("/getall")
    public ResponseEntity<List<Vacante>> getAll() {
        return new ResponseEntity<>(vacanteService.obtenerVacantes(), HttpStatus.OK);
    }

    @PostMapping("/addorupdate")
    public String addVacante(@RequestBody Vacante vacante) {
        vacanteService.addOrUpdateVacante(vacante);
        return "Vacante agregada/actualizada";
    }

}
