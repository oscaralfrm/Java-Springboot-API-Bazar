package com.todocode.ProyectoFinalSpringboot.services;

import com.todocode.ProyectoFinalSpringboot.dto.VentaDTO;
import com.todocode.ProyectoFinalSpringboot.models.Cliente;
import com.todocode.ProyectoFinalSpringboot.models.Producto;
import com.todocode.ProyectoFinalSpringboot.models.Venta;
import com.todocode.ProyectoFinalSpringboot.repositories.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVentaRepository ventaRepository;

    @Override
    public void createVenta(Venta venta) {
        ventaRepository.save(venta);
    }

    @Override
    public void actualizarStock(Venta venta) {
        List<Producto> listaProductos = this.getVenta(venta.getCodigo_venta()).getListaProductos();
        for (Producto producto : listaProductos) {
            Double cantidad_producto_disponible = producto.getCantidad_disponible();
            if (cantidad_producto_disponible > 0) {
                producto.setCantidad_disponible(cantidad_producto_disponible - 1);
            } else {
                listaProductos.remove(producto);
            }
        }
    }

    @Override
    public List<Venta> getAllVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta getVenta(Long codigo_venta) {
        return ventaRepository.findById(codigo_venta).orElse(null);
    }

    @Override
    public String getInformacionVentaDia(LocalDate fecha_dia) {

        List<Venta> listaVentas = this.getAllVentas();

        // Definición de acumuladores y contadores
        double total_facturado_dia = 0D;
        int contador_ventas = 0;

        for (Venta venta : listaVentas) {
            if (venta.getFecha_venta().isEqual(fecha_dia)) {
                total_facturado_dia += venta.getTotal();
                contador_ventas++;
            }
        }

        return "TOTAL DEL DÍA: " + total_facturado_dia + " | CANTIDAD VENTAS DEL DÍA: " + contador_ventas;


    }

    @Override
    public List<Producto> getProductosFromVenta(Long codigo_venta) {
        return this.getVenta(codigo_venta).getListaProductos();
    }

    @Override
    public VentaDTO getMayorVenta() {
        List<Venta> listaVentas = this.getAllVentas();
        VentaDTO ventaDTO = new VentaDTO();
        Double montoMayor = 0D;

        for (int i = 0; i < listaVentas.size(); i++) {
            Venta venta = listaVentas.get(i);
            if (venta.getTotal() > montoMayor) {
                montoMayor = venta.getTotal();
                ventaDTO.setCodigo_venta(venta.getCodigo_venta());
                ventaDTO.setTotal_venta(venta.getTotal());
                ventaDTO.setCantidad_productos_venta(venta.getListaProductos().size());
                ventaDTO.setNombre_cliente(venta.getCliente().getNombre());
                ventaDTO.setApellido_cliente(venta.getCliente().getApellido());
            }
        }

        return ventaDTO;
    }

    @Override
    public void explicitUpdateVenta(Long original_codigo_venta, Long new_codigo_venta, LocalDate new_fecha_venta, Double new_total_venta, List<Producto> new_lista_productos_venta, Cliente new_cliente_venta) {

        // FIND
        Venta venta = this.getVenta(original_codigo_venta);

        // SET

        venta.setCodigo_venta(new_codigo_venta);
        venta.setFecha_venta(new_fecha_venta);
        venta.setTotal(new_total_venta);
        venta.setListaProductos(new_lista_productos_venta);
        venta.setCliente(new_cliente_venta);


        // SAVE
        this.createVenta(venta);

    }

    @Override
    public void implicitUpdateVenta(Venta venta) {
        this.createVenta(venta);
    }

    @Override
    public void deleteVenta(Long codigo_venta) {
        ventaRepository.deleteById(codigo_venta);
    }
}
