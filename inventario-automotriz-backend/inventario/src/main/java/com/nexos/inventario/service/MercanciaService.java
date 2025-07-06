package com.nexos.inventario.service;

import com.nexos.inventario.dto.MercanciaDTO;
import java.time.LocalDate;

import java.util.List;

public interface MercanciaService {

    List<MercanciaDTO> listarMercancia();

    MercanciaDTO registrar(MercanciaDTO dto);

    MercanciaDTO editar(Long id, MercanciaDTO dto);

    void eliminar(Long id, Long usuarioId);
    
    MercanciaDTO buscar(Long id); 
    
    List<MercanciaDTO> buscarPorFiltros(String nombre, LocalDate fecha, Long usuarioId);
    
    boolean existePorNombre(String nombre);


}
