package com.todocode.ProyectoFinalSpringboot.controllers;

import com.todocode.ProyectoFinalSpringboot.models.Producto;
import com.todocode.ProyectoFinalSpringboot.models.Venta;
import com.todocode.ProyectoFinalSpringboot.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    // CREATE

    @PostMapping("/productos/crear")
    @ResponseBody
    public String createProducto(@RequestBody Producto producto) {

        productoService.createProducto(producto);
        return "Producto creado exitosamente";
    }

    // READ - ALL

    @GetMapping("/productos")
    @ResponseBody
    public List<Producto> getProductos() {
        return productoService.getAllProductos();
    }

    // READ - ONE

    @GetMapping("/productos/{codigo_producto}")
    @ResponseBody
    public Producto getProducto(@PathVariable Long codigo_producto) {
        return productoService.getProducto(codigo_producto);
    }

    // READ - PRODUCTOS ESCASOS

    @GetMapping("/productos/falta_stock")
    @ResponseBody
    public List<Producto> getProductosEscasos() {
        return productoService.getProductosEscasos();
    }

    // UPDATE - EXPLICIT

    @PutMapping("/productos/editar/{original_codigo_producto}")
    @ResponseBody
    public String explicitUpdateProducto(@PathVariable Long original_codigo_producto,
    @RequestParam (required = false, name = "codigo") Long new_codigo_producto,
    @RequestParam (required = false, name = "nombre") String new_nombre_producto,
    @RequestParam (required = false, name = "marca") String new_marca_producto,
    @RequestParam (required = false, name = "costo") Double new_costo_producto,
    @RequestParam (required = false, name = "cantidad") Double new_cantidad_producto,
    @RequestParam (required = false, name = "venta") Venta new_venta_producto) {

        productoService.explicitUpdateProducto(
                original_codigo_producto, new_codigo_producto,
                new_nombre_producto, new_marca_producto,
                new_costo_producto, new_cantidad_producto,
                new_venta_producto
        );

        return "Producto modificado exitosamente.";

    }

    // UPDATE- IMPLICIT

    @PutMapping("/productos/editar")
    @ResponseBody
    public String implicitUpdateProducto(@RequestParam Producto producto) {
        productoService.implicitUpdateProducto(producto);

        return "Producto modificado exitosamente.";
    }

    // DELETE

    @DeleteMapping("/productos/eliminar/{codigo_producto}")
    @ResponseBody
    public String deleteProducto(@PathVariable Long codigo_producto) {
        productoService.deleteProducto(codigo_producto);

        return "Producto eliminado exitosamente.";
    }

}
