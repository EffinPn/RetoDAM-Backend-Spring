package org.example.retodam.service;


import org.example.retodam.model.Empresa;

import java.util.Optional;

public interface EmpresaService {
    Optional<Empresa> encontrarPorId(Integer id);
}
