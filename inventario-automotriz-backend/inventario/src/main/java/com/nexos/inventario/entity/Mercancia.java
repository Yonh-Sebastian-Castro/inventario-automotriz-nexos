package com.nexos.inventario.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "mercancia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mercancia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private int cantidad;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @ManyToOne
    @JoinColumn(name = "usuario_registra_id", nullable = false)
    private Usuario usuarioRegistra;

    @ManyToOne
    @JoinColumn(name = "usuario_modifica_id")
    private Usuario usuarioModifica;

    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;
}
