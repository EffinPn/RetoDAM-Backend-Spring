package org.example.retodam.controller;

import org.example.retodam.dto.UsuarioDTO;
import org.example.retodam.model.Usuario;
import org.example.retodam.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    // Endpoint de registro de usuario ANDROID
    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        if(usuarioService.findByUsername(usuarioDTO.getUsername()) != null){
            return new ResponseEntity<>("El username elegido ya existe", HttpStatus.CONFLICT);
        } else if(usuarioService.findByEmail(usuarioDTO.getEmail()) != null){
            return new ResponseEntity<>("El email elegido ya existe", HttpStatus.CONFLICT);
        } else {
            usuarioService.registrarUsuario(usuarioDTO);
            return new ResponseEntity<>("Usuario registrado con éxito", HttpStatus.CREATED);
        }
    }

    // Endpoint de login -> ponemos que el response es un object para poder mandar un string en caso de que el login sea erroneo
    @GetMapping("/login")
    public ResponseEntity<Object> getLogin(@RequestParam String email, @RequestParam String password){

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail(email);
        usuarioDTO.setPassword(password);

        Usuario usuario = usuarioService.getLogin(usuarioDTO);

        if (usuario!=null){
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Correo o contraseña incorrectos", HttpStatus.UNAUTHORIZED);
        }
    }
}
