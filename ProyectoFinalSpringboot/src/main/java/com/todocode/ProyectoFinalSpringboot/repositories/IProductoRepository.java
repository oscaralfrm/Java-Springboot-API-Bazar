package com.todocode.ProyectoFinalSpringboot.repositories;

import com.todocode.ProyectoFinalSpringboot.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
}
