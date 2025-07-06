package com.nexos.inventario.repository;

import com.nexos.inventario.entity.Mercancia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MercanciaRepository extends JpaRepository<Mercancia, Long> {

    boolean existsByNombre(String nombre);

    @Query("SELECT m FROM Mercancia m " +
           "WHERE (:nombre IS NULL OR LOWER(m.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
           "AND (:fechaInicio IS NULL OR m.fechaIngreso >= :fechaInicio) " +
           "AND (:fechaFin IS NULL OR m.fechaIngreso <= :fechaFin) " +
           "AND (:usuarioRegistraId IS NULL OR m.usuarioRegistra.id = :usuarioRegistraId) " +
           "AND (:usuarioModificaId IS NULL OR m.usuarioModifica.id = :usuarioModificaId)")
    List<Mercancia> buscarMercanciaAvanzado(
            @Param("nombre") String nombre,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin,
            @Param("usuarioRegistraId") Long usuarioRegistraId,
            @Param("usuarioModificaId") Long usuarioModificaId
    );
}
