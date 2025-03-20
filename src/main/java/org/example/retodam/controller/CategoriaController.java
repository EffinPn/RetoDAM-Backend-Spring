package org.example.retodam.controller;

import org.example.retodam.model.Categoria;
import org.example.retodam.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/getall")
    public ResponseEntity<List<Categoria>> getAll() {
        return new ResponseEntity<>(categoriaService.obtenerCategorias(), HttpStatus.OK);
    }

}
