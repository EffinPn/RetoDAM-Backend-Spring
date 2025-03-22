package org.example.retodam.service;

import org.example.retodam.model.Categoria;

import java.util.List;

public interface CategoriaService {

    List<Categoria> obtenerCategorias();
    void addOrUpdateCategoria(Categoria categoria);
    void deleteCategoria(Categoria categoria);
}
