package com.nexos.inventario.dto;

import java.time.LocalDate;

public class MercanciaDTO {

    private Long id;
    private String nombre;
    private int cantidad;
    private LocalDate fechaIngreso;
    private Long usuarioRegistraId;
    private Long usuarioModificaId;
    private LocalDate fechaModificacion;

    public MercanciaDTO() {
        // Constructor vacío requerido por Jackson
    }

    public MercanciaDTO(Long id, String nombre, int cantidad, LocalDate fechaIngreso, Long usuarioRegistraId, Long usuarioModificaId, LocalDate fechaModificacion) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fechaIngreso = fechaIngreso;
        this.usuarioRegistraId = usuarioRegistraId;
        this.usuarioModificaId = usuarioModificaId;
        this.fechaModificacion = fechaModificacion;
    }

    public MercanciaDTO(Long id, String nombre, int cantidad, LocalDate fechaIngreso, Long usuarioRegistraId) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.fechaIngreso = fechaIngreso;
        this.usuarioRegistraId = usuarioRegistraId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Long getUsuarioRegistraId() {
        return usuarioRegistraId;
    }

    public void setUsuarioRegistraId(Long usuarioRegistraId) {
        this.usuarioRegistraId = usuarioRegistraId;
    }

    public Long getUsuarioModificaId() {
        return usuarioModificaId;
    }

    public void setUsuarioModificaId(Long usuarioModificaId) {
        this.usuarioModificaId = usuarioModificaId;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

}
