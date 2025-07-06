package com.nexos.inventario.repository;

import com.nexos.inventario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u " +
           "WHERE (:nombre IS NULL OR LOWER(u.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
           "AND (:minEdad IS NULL OR u.edad >= :minEdad) " +
           "AND (:maxEdad IS NULL OR u.edad <= :maxEdad) " +
           "AND (:cargoId IS NULL OR u.cargo.id = :cargoId) " +
           "AND (:fechaIngreso IS NULL OR u.fechaIngreso = :fechaIngreso)")
    List<Usuario> buscarUsuariosAvanzado(
            @Param("nombre") String nombre,
            @Param("minEdad") Integer minEdad,
            @Param("maxEdad") Integer maxEdad,
            @Param("cargoId") Long cargoId,
            @Param("fechaIngreso") LocalDate fechaIngreso
    );
}
