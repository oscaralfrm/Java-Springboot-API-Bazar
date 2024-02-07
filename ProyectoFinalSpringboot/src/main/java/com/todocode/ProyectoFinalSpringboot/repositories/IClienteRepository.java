package com.todocode.ProyectoFinalSpringboot.repositories;

import com.todocode.ProyectoFinalSpringboot.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {
}
