package com.todocode.ProyectoFinalSpringboot.controllers;

import com.todocode.ProyectoFinalSpringboot.dto.VentaDTO;
import com.todocode.ProyectoFinalSpringboot.models.Cliente;
import com.todocode.ProyectoFinalSpringboot.models.Producto;
import com.todocode.ProyectoFinalSpringboot.models.Venta;
import com.todocode.ProyectoFinalSpringboot.services.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    // CREATE

    @PostMapping("/ventas/crear")
    @ResponseBody
    public String createVenta(@RequestBody Venta venta) {
        ventaService.createVenta(venta);
        return "Venta creada exitosamente";
    }

    // READ - ALL

    @GetMapping("/ventas")
    @ResponseBody
    public List<Venta> getVentas() {
        return ventaService.getAllVentas();
    }

    // READ - ONE

    @GetMapping("/ventas/{codigo_venta}")
    @ResponseBody
    public Venta getVenta(@PathVariable Long codigo_venta) {
        return ventaService.getVenta(codigo_venta);
    }

    // READ - PRODUCTOS DE UNA VENTA

    @GetMapping("/ventas/productos/{codigo_venta}")
    @ResponseBody
    public List<Producto> getProductosFromVenta(@PathVariable Long codigo_venta) {
        return ventaService.getProductosFromVenta(codigo_venta);
    }

    // READ - VENTADTO

    @GetMapping("/ventas/mayor_venta")
    @ResponseBody
    public VentaDTO getMayorVenta() {
        return ventaService.getMayorVenta();
    }

    // READ - SUMATORIA MONTO Y CANT. VENTAS EN UN DÍA DETERMINADO CON LOCALDATE

    @GetMapping("/ventas/{fecha_dia}")
    @ResponseBody
    public String getInformacionVentaDia(@PathVariable LocalDate fecha_dia) {
        return ventaService.getInformacionVentaDia(fecha_dia);
    }

    // UPDATE - EXPLICIT

    @PutMapping("/ventas/editar/{original_codigo_venta}")
    @ResponseBody
    public String explicitUpdateVenta(@PathVariable Long original_codigo_venta,
                                    @RequestParam (required = false, name = "codigo") Long new_codigo_venta,
                                    @RequestParam (required = false, name = "fecha") LocalDate new_fecha_venta,
                                    @RequestParam (required = false, name = "total") Double new_total_venta,
                                    @RequestParam (required = false, name = "productos") List<Producto> new_lista_productos_venta,
                                    @RequestParam (required = false, name = "cliente") Cliente new_cliente_venta
                                    ) {
        ventaService.explicitUpdateVenta(original_codigo_venta, new_codigo_venta, new_fecha_venta, new_total_venta, new_lista_productos_venta, new_cliente_venta);
        return "Venta eliminada exitosamente";
    }

    // UPDATE - IMPLICIT
    @PutMapping("/ventas/editar")
    @ResponseBody
    public String implicitUpdateVenta(@RequestBody Venta venta) {
        ventaService.implicitUpdateVenta(venta);
        return "Venta modificada exitosamente";
    }

    // DELETE

    @DeleteMapping("/ventas/eliminar/{codigo_venta}")
    @ResponseBody
    public String deleteVenta(@PathVariable Long codigo_venta) {
        ventaService.deleteVenta(codigo_venta);
        return "Venta eliminada exitosamente";
    }

}
