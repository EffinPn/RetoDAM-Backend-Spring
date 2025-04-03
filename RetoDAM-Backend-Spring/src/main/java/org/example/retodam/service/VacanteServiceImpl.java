package org.example.retodam.service;

import org.example.retodam.dto.VacanteDTO;
import org.example.retodam.model.Vacante;
import org.example.retodam.repository.VacanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    // Para convertir lo de la BBDD a DTO
    @Override
    public List<VacanteDTO> vacantesToDTO(List<Vacante> vacantes) {
        List<VacanteDTO> vacantesDTO = new ArrayList<>();

        // convertimos cada vacante a DTO
        for(Vacante vacante : vacantes){
            VacanteDTO vacanteDTO = new VacanteDTO(
                    vacante.getNombre(),
                    vacante.getDescripcion(),
                    vacante.getFecha(),
                    vacante.getSalario(),
                    vacante.getEstatus(),
                    vacante.isDestacado(),
                    vacante.getImagen(),
                    vacante.getDetalles(),
                    vacante.getCategoria().getId_categoria(),
                    vacante.getEmpresa().getId_empresa()
            );

            vacantesDTO.add(vacanteDTO);
        }
        return vacantesDTO;
    }


    // Buscar vacante para solicitud
    @Override
    public Vacante getById(int id) {
        return vacanteRepository.findById(id).orElse(null);
    }

}
