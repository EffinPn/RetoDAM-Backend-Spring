package org.example.retodam.service;

import org.example.retodam.model.Empresa;
import org.example.retodam.model.Vacante;

import java.util.List;

public interface EmpresaService {

    List<Empresa> obtenerEmpresas();
    void addOrUpdateEmpresa(Empresa empresa);
    void borrarEmpresa(Empresa empresa);
}
