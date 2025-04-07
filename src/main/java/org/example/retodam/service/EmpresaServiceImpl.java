package org.example.retodam.service;

import org.example.retodam.model.Empresa;
import org.example.retodam.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;

    @Override
    public Optional<Empresa> encontrarPorId(Integer id) {
        return empresaRepository.findByIdEmpresa(id);
    }
}
