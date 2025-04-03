package org.example.retodam.controller;

import org.example.retodam.dto.VacanteDTO;
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

    // Endpoint para buscar Vacantes con filtros ANDROID
    @GetMapping("/buscarFiltros")
    public ResponseEntity<List<Vacante>> listarVacantesFiltros(@RequestParam(required = false) String empresa,
                                                               @RequestParam(required = false) String categoria,
                                                               @RequestParam(required = false) String descripcion){

        List<Vacante> vacantes = vacanteService.listarVacantesFiltros(empresa, categoria, descripcion);

        List<VacanteDTO> vacantesDTO = vacanteService.vacantesToDTO(vacantes);

        if (!vacantesDTO.isEmpty()) {
            return new ResponseEntity<>(vacantes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
