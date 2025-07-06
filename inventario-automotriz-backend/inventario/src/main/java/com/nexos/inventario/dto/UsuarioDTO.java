package com.nexos.inventario.dto;

import java.time.LocalDate;

public class UsuarioDTO {

    private Long id;
    private String nombre;
    private int edad;
    private LocalDate fechaIngreso;
    private Long cargoId;
    private String nombreCargo;

    public UsuarioDTO(Long id, String nombre, int edad, LocalDate fechaIngreso, Long cargoId) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.fechaIngreso = fechaIngreso;
        this.cargoId = cargoId;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Long getCargoId() {
        return cargoId;
    }

    public void setCargoId(Long cargoId) {
        this.cargoId = cargoId;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
    }
}
