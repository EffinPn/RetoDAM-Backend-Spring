package org.example.retodam.controller;

import org.example.retodam.model.Vacante;
import org.example.retodam.service.VacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

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

}
