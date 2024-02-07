package com.todocode.ProyectoFinalSpringboot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Producto {

    // Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo_producto;
    private String nombre;
    private String marca;
    private Double costo;
    private Double cantidad_disponible;
    @ManyToOne
    @JoinColumn(name = "codigo_venta")
    private Venta venta;

}
