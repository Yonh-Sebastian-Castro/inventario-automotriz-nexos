package com.nexos.inventario.controller;

import com.nexos.inventario.dto.MercanciaDTO;
import com.nexos.inventario.service.MercanciaService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import org.springframework.format.annotation.DateTimeFormat;

@RestController
@RequestMapping("/api/mercancia")
public class MercanciaController {

    @Autowired
    private MercanciaService mercanciaService;

    // Obtener todas las mercancías
    @GetMapping
    public ResponseEntity<List<MercanciaDTO>> obtenerTodas() {
        List<MercanciaDTO> lista = mercanciaService.listarMercancia();
        return ResponseEntity.ok(lista);
    }

    // Crear nueva mercancía
    @PostMapping
    public ResponseEntity<MercanciaDTO> crear(@Valid @RequestBody MercanciaDTO dto) {
        MercanciaDTO creada = mercanciaService.registrar(dto);
        return ResponseEntity.ok(creada);
    }

    // Actualizar mercancía
    @PutMapping("/{id}")
    public ResponseEntity<MercanciaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody MercanciaDTO dto) {
        MercanciaDTO actualizada = mercanciaService.editar(id, dto);
        return ResponseEntity.ok(actualizada);
    }

    // Eliminar mercancia
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Long id, @RequestParam Long usuarioId) {
        mercanciaService.eliminar(id, usuarioId);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Mercancía eliminada correctamente.");
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MercanciaDTO> buscarPorId(@PathVariable Long id) {
        MercanciaDTO dto = mercanciaService.buscar(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/buscar")
    public List<MercanciaDTO> buscarMercancias(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam(required = false) Long usuarioId) {

        if (nombre == null && fecha == null && usuarioId == null) {
            throw new IllegalArgumentException("Debe proporcionar al menos un criterio de búsqueda.");
        }

        return mercanciaService.buscarPorFiltros(nombre, fecha, usuarioId);
    }

    @GetMapping("/existe")
    public ResponseEntity<Boolean> nombreExiste(@RequestParam String nombre) {
        boolean existe = mercanciaService.existePorNombre(nombre);
        return ResponseEntity.ok(existe);
    }

}
