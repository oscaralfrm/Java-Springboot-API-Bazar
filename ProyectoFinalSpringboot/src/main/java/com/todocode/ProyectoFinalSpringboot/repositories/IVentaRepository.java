package com.todocode.ProyectoFinalSpringboot.repositories;

import com.todocode.ProyectoFinalSpringboot.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Long> {
}
