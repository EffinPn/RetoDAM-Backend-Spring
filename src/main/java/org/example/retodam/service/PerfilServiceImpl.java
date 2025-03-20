package org.example.retodam.service;

import org.example.retodam.model.Perfil;
import org.example.retodam.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilServiceImpl implements PerfilService {

    @Autowired
    PerfilRepository perfilRepository;

    @Override
    public List<Perfil> obtenerPerfiles() {
        return perfilRepository.findAll();
    }
}
