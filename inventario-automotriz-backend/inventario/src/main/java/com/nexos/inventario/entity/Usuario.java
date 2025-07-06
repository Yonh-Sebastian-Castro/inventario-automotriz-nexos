package com.nexos.inventario.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private int edad;

    @Column(name = "fecha_ingreso")
    private LocalDate fechaIngreso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;

}
