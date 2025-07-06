package com.nexos.inventario.service.impl;

import com.nexos.inventario.dto.MercanciaDTO;
import com.nexos.inventario.entity.Mercancia;
import com.nexos.inventario.entity.Usuario;
import com.nexos.inventario.repository.MercanciaRepository;
import com.nexos.inventario.repository.UsuarioRepository;
import com.nexos.inventario.service.MercanciaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MercanciaServiceImpl implements MercanciaService {

    @Autowired
    private MercanciaRepository mercanciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<MercanciaDTO> listarMercancia() {
        return mercanciaRepository.findAll()
                .stream()
                .map(m -> new MercanciaDTO(
                m.getId(),
                m.getNombre(),
                m.getCantidad(),
                m.getFechaIngreso(),
                m.getUsuarioRegistra().getId(),
                m.getUsuarioModifica() != null ? m.getUsuarioModifica().getId() : null,
                m.getFechaModificacion()
        ))
                .collect(Collectors.toList());
    }

    @Override
    public MercanciaDTO registrar(MercanciaDTO dto) {
        if (mercanciaRepository.existsByNombre(dto.getNombre())) {
            throw new IllegalArgumentException("Ya existe una mercancía con ese nombre.");
        }
        if (dto.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser futura.");
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioRegistraId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado."));

        Mercancia m = new Mercancia();
        m.setNombre(dto.getNombre());
        m.setCantidad(dto.getCantidad());
        m.setFechaIngreso(dto.getFechaIngreso());
        m.setUsuarioRegistra(usuario);

        Mercancia guardada = mercanciaRepository.save(m);
        return new MercanciaDTO(guardada.getId(), guardada.getNombre(), guardada.getCantidad(),
                guardada.getFechaIngreso(), guardada.getUsuarioRegistra().getId());
    }

    @Override
    public MercanciaDTO editar(Long id, MercanciaDTO dto) {
        Mercancia existente = mercanciaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mercancía no encontrada."));

        if (!existente.getNombre().equals(dto.getNombre())
                && mercanciaRepository.existsByNombre(dto.getNombre())) {
            throw new IllegalArgumentException("El nombre ya está en uso.");
        }

        if (dto.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser futura.");
        }

        Usuario usuarioModificador = usuarioRepository.findById(dto.getUsuarioModificaId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario modificador no encontrado."));

        existente.setNombre(dto.getNombre());
        existente.setCantidad(dto.getCantidad());
        existente.setFechaIngreso(dto.getFechaIngreso());
        existente.setUsuarioModifica(usuarioModificador);
        existente.setFechaModificacion(LocalDate.now());

        Mercancia actualizada = mercanciaRepository.save(existente);

        return new MercanciaDTO(
                actualizada.getId(),
                actualizada.getNombre(),
                actualizada.getCantidad(),
                actualizada.getFechaIngreso(),
                actualizada.getUsuarioRegistra().getId(),
                actualizada.getUsuarioModifica() != null ? actualizada.getUsuarioModifica().getId() : null,
                actualizada.getFechaModificacion()
        );
    }

    @Override
    @Transactional
    public void eliminar(Long mercanciaId, Long usuarioIdSolicitante) {
        Mercancia mercancia = mercanciaRepository.findById(mercanciaId)
                .orElseThrow(() -> new IllegalArgumentException("Mercancía no encontrada."));

        if (!mercancia.getUsuarioRegistra().getId().equals(usuarioIdSolicitante)) {
            throw new IllegalArgumentException("Solo el usuario que registró la mercancía puede eliminarla.");
        }

        mercanciaRepository.delete(mercancia);
    }

    @Override
    public MercanciaDTO buscar(Long id) {
        Mercancia mercancia = mercanciaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mercancía no encontrada."));

        return new MercanciaDTO(
                mercancia.getId(),
                mercancia.getNombre(),
                mercancia.getCantidad(),
                mercancia.getFechaIngreso(),
                mercancia.getUsuarioRegistra().getId(),
                mercancia.getUsuarioModifica() != null ? mercancia.getUsuarioModifica().getId() : null,
                mercancia.getFechaModificacion()
        );
    }

    @Override
    public List<MercanciaDTO> buscarPorFiltros(String nombre, LocalDate fecha, Long usuarioId) {
        List<Mercancia> filtradas = mercanciaRepository.findAll().stream()
                .filter(m -> (nombre == null || m.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                && (fecha == null || m.getFechaIngreso().equals(fecha))
                && (usuarioId == null || m.getUsuarioRegistra().getId().equals(usuarioId)))
                .collect(Collectors.toList());

        return filtradas.stream()
                .map(m -> new MercanciaDTO(m.getId(), m.getNombre(), m.getCantidad(), m.getFechaIngreso(),
                m.getUsuarioRegistra().getId(),
                m.getUsuarioModifica() != null ? m.getUsuarioModifica().getId() : null,
                m.getFechaModificacion()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return mercanciaRepository.existsByNombre(nombre);
    }

}
