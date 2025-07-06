package com.nexos.inventario.service;

import com.nexos.inventario.dto.UsuarioDTO;

import java.time.LocalDate;
import java.util.List;

public interface UsuarioService {

    List<UsuarioDTO> listarUsuarios();

    UsuarioDTO registrar(UsuarioDTO dto);

    UsuarioDTO editar(Long id, UsuarioDTO dto);

    void eliminar(Long id);

    List<UsuarioDTO> buscarUsuariosAvanzado(String nombre, Integer minEdad, Integer maxEdad, Long cargoId, LocalDate fechaIngreso);

    UsuarioDTO buscarPorId(Long id);
}
