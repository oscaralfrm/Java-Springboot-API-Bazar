package com.todocode.ProyectoFinalSpringboot.controllers;

import com.todocode.ProyectoFinalSpringboot.models.Cliente;
import com.todocode.ProyectoFinalSpringboot.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    // CREATE

    @PostMapping("/clientes/crear")
    @ResponseBody
    public String createCliente(@RequestBody Cliente cliente) {

        clienteService.createCliente(cliente);
        return "Cliente creado exitosamente";
    }

    // READ - ALL

    @GetMapping("/clientes")
    @ResponseBody
    public List<Cliente> getClientes() {
        return clienteService.getAllClientes();
    }

    // READ - ONE
    @GetMapping("/clientes/{id_cliente}")
    @ResponseBody
    public Cliente getCliente(@PathVariable Long id_cliente) {
        return clienteService.getCliente(id_cliente);
    }

    // UPDATE - EXPLICIT

    @PutMapping("/clientes/editar/{original_id_cliente}")
    @ResponseBody
    public String explicitUpdateCliente(@PathVariable Long original_id_cliente,
                                      @RequestParam (required = false, name = "id") Long new_id_cliente,
                                      @RequestParam (required = false, name = "nombre") String new_nombre_cliente,
                                      @RequestParam (required = false, name = "apellido") String new_apellido_cliente,
                                      @RequestParam (required = false, name = "dni") String new_dni_cliente) {

        clienteService.explicitUpdateCliente(original_id_cliente, new_id_cliente, new_nombre_cliente, new_apellido_cliente, new_dni_cliente);

        return "Cliente modificado exitosamente";
    }

    // UPDATE - IMPLICIT

    @PutMapping("/clientes/editar")
    @ResponseBody
    public String implicitUpdateCliente(@RequestBody Cliente cliente) {
        clienteService.implicitUpdateCliente(cliente);
        return "Cliente modificado exitosamente";
    }

    // DELETE

    @DeleteMapping("/clientes/eliminar/{id_cliente}")
    @ResponseBody
    public String deleteCliente(@PathVariable Long id_cliente) {
        clienteService.deleteCliente(id_cliente);
        return "Cliente eliminado exitosamente";
    }


}
