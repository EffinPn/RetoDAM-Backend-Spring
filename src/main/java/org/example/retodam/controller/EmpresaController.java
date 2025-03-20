package org.example.retodam.controller;

import org.example.retodam.model.Empresa;
import org.example.retodam.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("empresas")
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;

    @GetMapping("/getall")
    public ResponseEntity<List<Empresa>> getAll() {
        return new ResponseEntity<>(empresaService.obtenerEmpresas(), HttpStatus.OK);
    }



}
