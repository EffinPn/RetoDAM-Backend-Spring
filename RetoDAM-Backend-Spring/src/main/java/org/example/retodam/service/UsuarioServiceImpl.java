package org.example.retodam.service;

import org.example.retodam.model.Usuario;
import org.example.retodam.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    // Añadir usuario para ANDROID
    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        
        return usuarioRepository.save(usuario);
    }

    // Buscar usuario para solicitudes y para ver si username ya existe
    @Override
    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    // Buscar por email para ver si ya existe
    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    // Login usuario
    @Override
    public Usuario getLogin(String email, String password) {
        return usuarioRepository.getLogin(email,password);
    }
}
