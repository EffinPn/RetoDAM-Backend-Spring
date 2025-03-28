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

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public void addOrUpdateUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> encontrarPorPerfilesId(int id) {
        return usuarioRepository.findByPerfiles_Id(id);
    }
}
