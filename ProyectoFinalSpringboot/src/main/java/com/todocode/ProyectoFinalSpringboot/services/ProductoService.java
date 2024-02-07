package com.todocode.ProyectoFinalSpringboot.services;

import com.todocode.ProyectoFinalSpringboot.models.Producto;
import com.todocode.ProyectoFinalSpringboot.models.Venta;
import com.todocode.ProyectoFinalSpringboot.repositories.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService implements  IProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public void createProducto(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public List<Producto> getProductosEscasos() {
        return this.getAllProductos().stream()
                .filter(producto -> producto.getCantidad_disponible() < 5)
                .collect(Collectors.toList());
    }

    @Override
    public Producto getProducto(Long codigo_producto) {
        return productoRepository.findById(codigo_producto).orElse(null);
    }

    @Override
    public void explicitUpdateProducto(Long original_codigo_producto, Long new_codigo_producto, String new_nombre_producto, String new_marca_producto, Double new_costo_producto, Double new_cantidad_disponible_producto, Venta new_venta_producto) {

        // FIND
        Producto producto = this.getProducto(original_codigo_producto);

        // SET

        producto.setCodigo_producto(new_codigo_producto);
        producto.setNombre(new_nombre_producto);
        producto.setMarca(new_marca_producto);
        producto.setCosto(new_costo_producto);
        producto.setCantidad_disponible(new_cantidad_disponible_producto);

        // SAVE
        this.createProducto(producto);


    }

    @Override
    public void implicitUpdateProducto(Producto producto) {
        this.createProducto(producto);
    }

    @Override
    public void deleteProducto(Long codigo_producto) {
        productoRepository.deleteById(codigo_producto);
    }
}
