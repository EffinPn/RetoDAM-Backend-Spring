package org.example.retodam.service;

import org.example.retodam.model.Empresa;
import org.example.retodam.model.Vacante;
import org.example.retodam.repository.EmpresaRepository;
import org.example.retodam.repository.VacanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;

}
