package com.todocode.ProyectoFinalSpringboot.services;

import com.todocode.ProyectoFinalSpringboot.models.Producto;
import com.todocode.ProyectoFinalSpringboot.models.Venta;

import java.util.List;

public interface IProductoService {

    // CREATE

    public void createProducto(Producto producto);

    // READ - ALL

    public List<Producto> getAllProductos();


    // READ - PRODUCTOS ESCASOS

    public List<Producto> getProductosEscasos();

    // READ

    public Producto getProducto(Long codigo_producto);

    // UPDATE - EXPLICIT

    public void explicitUpdateProducto(Long original_codigo_producto,
                                       Long new_codigo_producto,
                                       String new_nombre_producto,
                                       String new_marca_producto,
                                       Double new_costo_producto,
                                       Double new_cantidad_disponible_producto,
                                       Venta new_venta_producto);

    // UPDATE - IMPLICIT

    public void implicitUpdateProducto(Producto producto);

    // DELETE

    public void deleteProducto(Long codigo_producto);

}
