package org.example.retodam.controller;

import org.example.retodam.model.Usuario;
import org.example.retodam.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/getall")
    public ResponseEntity<List<Usuario>> getAll() {
        return new ResponseEntity<>(usuarioService.obtenerUsuarios(), HttpStatus.OK);
    }

}
