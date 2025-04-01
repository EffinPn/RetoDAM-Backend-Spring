package org.example.retodam.service;

import org.example.retodam.model.Usuario;

import java.util.List;

public interface UsuarioService {

    // Añadir usuario para ANDROID
    Usuario registrarUsuario(Usuario usuario);

    // Buscar usuario para añadir la solicitud y para comprobar si ya existe el username
    Usuario findByUsername(String username);

    // Buscar por correo para comprobar si ya existe
    Usuario findByEmail(String email);

    // Login de usuario
    Usuario getLogin(String email, String password);
}
