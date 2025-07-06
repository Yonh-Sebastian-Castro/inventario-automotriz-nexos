package com.nexos.inventario.service;

import com.nexos.inventario.dto.CargoDTO;

import java.util.List;

public interface CargoService {

    List<CargoDTO> listarCargos();

    CargoDTO crearCargo(CargoDTO dto);
}
