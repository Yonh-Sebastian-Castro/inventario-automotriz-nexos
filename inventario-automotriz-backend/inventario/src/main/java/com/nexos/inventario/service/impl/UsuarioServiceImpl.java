package com.nexos.inventario.service.impl;

import com.nexos.inventario.dto.UsuarioDTO;
import com.nexos.inventario.entity.Cargo;
import com.nexos.inventario.entity.Usuario;
import com.nexos.inventario.repository.CargoRepository;
import com.nexos.inventario.repository.UsuarioRepository;
import com.nexos.inventario.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Override
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> {
                    UsuarioDTO dto = new UsuarioDTO(
                            usuario.getId(),
                            usuario.getNombre(),
                            usuario.getEdad(),
                            usuario.getFechaIngreso(),
                            usuario.getCargo().getId()
                    );
                    dto.setNombreCargo(usuario.getCargo().getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO registrar(UsuarioDTO dto) {
        Cargo cargo = cargoRepository.findById(dto.getCargoId())
                .orElseThrow(() -> new EntityNotFoundException("Cargo no encontrado."));

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEdad(dto.getEdad());
        usuario.setFechaIngreso(dto.getFechaIngreso());
        usuario.setCargo(cargo);

        Usuario guardado = usuarioRepository.save(usuario);
        UsuarioDTO dtoResp = new UsuarioDTO(
                guardado.getId(),
                guardado.getNombre(),
                guardado.getEdad(),
                guardado.getFechaIngreso(),
                guardado.getCargo().getId()
        );
        dtoResp.setNombreCargo(guardado.getCargo().getNombre());
        return dtoResp;
    }

    @Override
    public UsuarioDTO editar(Long id, UsuarioDTO dto) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado."));

        Cargo cargo = cargoRepository.findById(dto.getCargoId())
                .orElseThrow(() -> new EntityNotFoundException("Cargo no encontrado."));

        usuarioExistente.setNombre(dto.getNombre());
        usuarioExistente.setEdad(dto.getEdad());
        usuarioExistente.setFechaIngreso(dto.getFechaIngreso());
        usuarioExistente.setCargo(cargo);

        Usuario actualizado = usuarioRepository.save(usuarioExistente);
        UsuarioDTO dtoResp = new UsuarioDTO(
                actualizado.getId(),
                actualizado.getNombre(),
                actualizado.getEdad(),
                actualizado.getFechaIngreso(),
                actualizado.getCargo().getId()
        );
        dtoResp.setNombreCargo(actualizado.getCargo().getNombre());
        return dtoResp;
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado."));
        usuarioRepository.delete(usuario);
    }

    @Override
    public List<UsuarioDTO> buscarUsuariosAvanzado(String nombre, Integer minEdad, Integer maxEdad, Long cargoId, LocalDate fechaIngreso) {
        return usuarioRepository.buscarUsuariosAvanzado(nombre, minEdad, maxEdad, cargoId, fechaIngreso)
                .stream()
                .map(u -> {
                    UsuarioDTO dto = new UsuarioDTO(
                            u.getId(),
                            u.getNombre(),
                            u.getEdad(),
                            u.getFechaIngreso(),
                            u.getCargo().getId()
                    );
                    dto.setNombreCargo(u.getCargo().getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado."));

        UsuarioDTO dto = new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEdad(),
                usuario.getFechaIngreso(),
                usuario.getCargo().getId()
        );
        dto.setNombreCargo(usuario.getCargo().getNombre());
        return dto;
    }

}
