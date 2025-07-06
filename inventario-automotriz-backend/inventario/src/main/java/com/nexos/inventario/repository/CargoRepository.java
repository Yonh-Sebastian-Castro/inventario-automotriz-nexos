package com.nexos.inventario.repository;

import com.nexos.inventario.entity.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
    boolean existsByNombre(String nombre);
}
