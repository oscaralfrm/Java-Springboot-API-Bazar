package com.todocode.ProyectoFinalSpringboot.services;

import com.todocode.ProyectoFinalSpringboot.dto.VentaDTO;
import com.todocode.ProyectoFinalSpringboot.models.Cliente;
import com.todocode.ProyectoFinalSpringboot.models.Producto;
import com.todocode.ProyectoFinalSpringboot.models.Venta;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService {

    // CREATE

    public void createVenta(Venta venta);

    // CREATE - ACTUALIZAR STOCK

    public void actualizarStock(Venta venta);


    // READ - ALL

    public List<Venta> getAllVentas();

    // READ - ONE

    public Venta getVenta(Long codigo_venta);

    // READ - SUMATORIA MONTO Y CANT. VENTAS EN UN DÍA DETERMINADO CON LOCALDATE

    public String getInformacionVentaDia(LocalDate fecha_dia);

    // READ - PRODUCTOS DE UNA VENTA

    public List<Producto> getProductosFromVenta(Long codigo_venta);

    // READ - VENTADTO // TOTAL MAYOR

    public VentaDTO getMayorVenta();

    // UPDATE - EXPLICIT

    public void explicitUpdateVenta(Long original_codigo_venta,
                                    Long new_codigo_venta,
                                    LocalDate new_fecha_venta,
                                    Double new_total_venta,
                                    List<Producto> new_lista_productos_venta,
                                    Cliente new_cliente_venta);

    // UPDATE - IMPLICIT

    public void implicitUpdateVenta(Venta venta);

    // DELETE

    public void deleteVenta(Long codigo_venta);

}
