package com.nexos.inventario.controller;

import com.nexos.inventario.dto.UsuarioDTO;
import com.nexos.inventario.service.UsuarioService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

@RestController
@RequestMapping("/api/usuarios")

public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping
    public UsuarioDTO registrarUsuario(@Valid @RequestBody UsuarioDTO dto) {
        return usuarioService.registrar(dto);
    }

    @PutMapping("/{id}")
    public UsuarioDTO editarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) {
        return usuarioService.editar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }

    @GetMapping("/buscar")
    public List<UsuarioDTO> buscarUsuarios(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer minEdad,
            @RequestParam(required = false) Integer maxEdad,
            @RequestParam(required = false) Long cargoId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaIngreso
    ) {
        return usuarioService.buscarUsuariosAvanzado(nombre, minEdad, maxEdad, cargoId, fechaIngreso);
    }

}
