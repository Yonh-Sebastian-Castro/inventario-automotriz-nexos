package com.nexos.inventario.controller;

import com.nexos.inventario.entity.Cargo;
import com.nexos.inventario.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cargos")
public class CargoController {

    @Autowired
    private CargoRepository cargoRepository;

    @GetMapping
    public List<Cargo> listarCargos() {
        return cargoRepository.findAll();
    }
}
