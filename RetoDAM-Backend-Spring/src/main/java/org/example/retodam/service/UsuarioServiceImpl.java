package org.example.retodam.service;

import org.example.retodam.dto.UsuarioDTO;
import org.example.retodam.model.Usuario;
import org.example.retodam.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    // Añadir usuario para ANDROID
    @Override
    public Usuario registrarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();

        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellidos(usuarioDTO.getApellidos());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setFecha_registro(new Date());
        usuario.setEnabled(1);

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
    public Usuario getLogin(UsuarioDTO usuarioDTO) {
        return usuarioRepository.getLogin(usuarioDTO.getEmail(), usuarioDTO.getPassword());
    }
}
