package com.nexos.inventario.service.impl;

import com.nexos.inventario.dto.CargoDTO;
import com.nexos.inventario.entity.Cargo;
import com.nexos.inventario.repository.CargoRepository;
import com.nexos.inventario.service.CargoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    @Override
    public List<CargoDTO> listarCargos() {
        return cargoRepository.findAll()
                .stream()
                .map(cargo -> new CargoDTO(cargo.getId(), cargo.getNombre()))
                .collect(Collectors.toList());
    }

    @Override
    public CargoDTO crearCargo(CargoDTO dto) {
        if (cargoRepository.existsByNombre(dto.getNombre())) {
            throw new IllegalArgumentException("Ya existe un cargo con ese nombre.");
        }
        Cargo cargo = new Cargo();
        cargo.setNombre(dto.getNombre());
        Cargo guardado = cargoRepository.save(cargo);
        return new CargoDTO(guardado.getId(), guardado.getNombre());
    }
}
