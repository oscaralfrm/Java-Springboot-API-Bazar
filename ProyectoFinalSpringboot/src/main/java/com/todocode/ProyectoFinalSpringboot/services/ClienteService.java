package com.todocode.ProyectoFinalSpringboot.services;

import com.todocode.ProyectoFinalSpringboot.models.Cliente;
import com.todocode.ProyectoFinalSpringboot.repositories.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    public void createCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getCliente(Long id_cliente) {
        return clienteRepository.findById(id_cliente).orElse(null);
    }

    @Override
    public void explicitUpdateCliente(Long original_id_cliente, Long new_id_cliente, String new_nombre, String new_apellido, String new_dni) {

        // FIND

        Cliente cliente = this.getCliente(original_id_cliente);

        // SET

        cliente.setId_cliente(new_id_cliente);
        cliente.setNombre(new_nombre);
        cliente.setApellido(new_apellido);
        cliente.setDni(new_dni);

        // SAVE

        this.createCliente(cliente);

    }

    @Override
    public void implicitUpdateCliente(Cliente cliente) {
        this.createCliente(cliente);
    }

    @Override
    public void deleteCliente(Long id_cliente) {
        clienteRepository.deleteById(id_cliente);
    }
}
