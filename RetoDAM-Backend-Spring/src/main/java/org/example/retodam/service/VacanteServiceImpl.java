package org.example.retodam.service;

import org.example.retodam.model.Vacante;
import org.example.retodam.repository.VacanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacanteServiceImpl implements VacanteService {

    @Autowired
    VacanteRepository vacanteRepository;

    // Listar vacantes con filtros ANDROID
    @Override
    public List<Vacante> listarVacantesFiltros(String empresa, String categoria, String descripcion) {
        return vacanteRepository.listarVacantesFiltros(empresa,categoria,descripcion);
    }

    // Buscar vacante para solicitud
    @Override
    public Vacante getById(int id) {
        return vacanteRepository.findById(id).orElse(null);
    }

}
