package org.example.retodam.service;

import org.example.retodam.model.Categoria;
import org.example.retodam.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;


    @Override
    public List<Categoria> obtenerCategorias() {
        return categoriaRepository.findAll();
    }
}
