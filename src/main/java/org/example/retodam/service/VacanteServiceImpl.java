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

    @Override
    public List<Vacante> obtenerVacantes() {
        return vacanteRepository.findAll();
    }

    @Override
    public void addOrUpdateVacante(Vacante vacante) {
        vacanteRepository.save(vacante);
    }
}
