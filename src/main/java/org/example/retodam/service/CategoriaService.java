package org.example.retodam.service;


import org.example.retodam.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    Optional<Categoria> encontrarPorId(int id);
    List<Categoria> encontrarTodas();
}
