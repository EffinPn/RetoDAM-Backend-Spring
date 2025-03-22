package org.example.retodam.service;

import org.example.retodam.model.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> obtenerUsuarios();
    void addOrUpdateUsuario(Usuario usuario);
    List<Usuario> encontrarPorPerfilesId(int id);
}
