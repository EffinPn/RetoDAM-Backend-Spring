package org.example.retodam.controller;

import org.example.retodam.model.Usuario;
import org.example.retodam.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/addorupdate")
    public String addorupdate(@RequestBody Usuario usuario) {
        usuarioService.addOrUpdateUsuario(usuario);
        return "Usuario agregado/actualizado";
    }

    @GetMapping("/getbyperfiles")
    public ResponseEntity<List<Usuario>> getByPerfiles(@RequestParam int id_perfil) {
        List<Usuario> users = usuarioService.encontrarPorPerfilesId(id_perfil);
        if(!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
