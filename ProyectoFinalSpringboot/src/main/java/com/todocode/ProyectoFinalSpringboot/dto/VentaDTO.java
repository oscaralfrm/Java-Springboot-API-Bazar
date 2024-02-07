package com.todocode.ProyectoFinalSpringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class VentaDTO {

    // De VENTA
    private Long codigo_venta;
    private Double total_venta;
    private Integer cantidad_productos_venta; // NOTA: Obtener de un List<Venta>
    // De CLIENTE
    private String nombre_cliente; // NOTA: Obtener de un List<Venta> según el total más alto
    private String apellido_cliente; // NOTA: Obtener de un List<Venta> según el total más alto


}
