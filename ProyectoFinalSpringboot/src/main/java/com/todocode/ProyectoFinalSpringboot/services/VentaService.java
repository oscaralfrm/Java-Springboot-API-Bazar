package com.todocode.ProyectoFinalSpringboot.services;

import com.todocode.ProyectoFinalSpringboot.dto.VentaDTO;
import com.todocode.ProyectoFinalSpringboot.models.Cliente;
import com.todocode.ProyectoFinalSpringboot.models.Producto;
import com.todocode.ProyectoFinalSpringboot.models.Venta;
import com.todocode.ProyectoFinalSpringboot.repositories.IClienteRepository;
import com.todocode.ProyectoFinalSpringboot.repositories.IProductoRepository;
import com.todocode.ProyectoFinalSpringboot.repositories.IVentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVentaRepository ventaRepository;

    // Necesitamos las persistencias de Cliente y Producto para hacer operaciones
    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    @Transactional
    public void createVenta(Venta venta) {

        // FIND del Cliente, para añadirlo a la venta

        Cliente cliente = clienteRepository.findById(venta.getCliente().getId_cliente()).orElse(null);

        // FIND de los productos para añadirlos a la venta

        List<Producto> listaProductos = productoRepository.findAll();


        // TOTAL - Cálculo del Total de la venta

        // Acumulador del Total
        Double total = 0D;

        for (Producto producto : listaProductos) {
            total += producto.getCosto();
            producto.setVenta(venta);

        }

        // SET - Setteamos las configuraciones que se enviarán al JSON


        venta.setCliente(cliente);
        venta.setListaProductos(listaProductos);
        venta.setTotal(total);

        // SAVE - Por último, guardamos

        ventaRepository.save(venta);

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
