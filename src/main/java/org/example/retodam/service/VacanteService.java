package org.example.retodam.service;

import org.example.retodam.dto.VacanteDTO;
import org.example.retodam.model.Vacante;

import java.util.List;

public interface VacanteService {

    // Listar vacantes con filtros ANDROID
    List<Vacante> listarVacantesFiltros(String empresa, String categoria, String descripcion);

    List<VacanteDTO> vacantesToDTO(List<Vacante> vacantes);
    List<Vacante> consultarVacantes();
    // buscar vacante para solicitud
    Vacante getById(int id);
}
