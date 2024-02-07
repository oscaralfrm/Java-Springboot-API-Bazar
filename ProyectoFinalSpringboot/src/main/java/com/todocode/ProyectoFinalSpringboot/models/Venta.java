package com.todocode.ProyectoFinalSpringboot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Venta {

    // Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo_venta;
    private LocalDate fecha_venta;
    private Double total;
    @OneToMany(mappedBy = "venta")
    private List<Producto> listaProductos;
    @OneToOne
    @JoinColumn(name = "cliente",
    referencedColumnName = "id_cliente")
    private Cliente cliente;

}
