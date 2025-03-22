package org.example.retodam.controller;

import org.example.retodam.model.Empresa;
import org.example.retodam.model.Vacante;
import org.example.retodam.service.EmpresaService;
import org.example.retodam.service.VacanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/addorupdate")
    public String AddOrUpdateEmpresa(@RequestBody Empresa empresa) {
        empresaService.addOrUpdateEmpresa(empresa);
        return "Empresa agregada/actualizada";
    }

    @PostMapping("/delete")
    public String DeleteEmpresa(@RequestBody Empresa empresa) {
        empresaService.borrarEmpresa(empresa);
        return "Empresa eliminada";
    }


}
