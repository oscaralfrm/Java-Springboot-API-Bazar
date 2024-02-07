package com.todocode.ProyectoFinalSpringboot.services;

import com.todocode.ProyectoFinalSpringboot.models.Cliente;

import java.util.List;

public interface IClienteService {

    // CREATE

    public void createCliente(Cliente cliente);

    // READ - ALL

    public List<Cliente> getAllClientes();

    // READ - ONE

    public Cliente getCliente(Long id_cliente);

    // UPDATE - EXPLICIT

    public void explicitUpdateCliente(Long original_id_cliente,
                                      Long new_id_cliente,
                                      String new_nombre,
                                      String new_apellido,
                                      String new_dni);

    // UPDATE - IMPLICIT

    public void implicitUpdateCliente(Cliente cliente);

    // DELETE

    public void deleteCliente(Long id_cliente);

}
