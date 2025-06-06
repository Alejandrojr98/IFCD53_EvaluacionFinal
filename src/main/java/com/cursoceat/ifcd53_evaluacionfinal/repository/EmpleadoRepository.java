package com.cursoceat.ifcd53_evaluacionfinal.repository;

import com.cursoceat.ifcd53_evaluacionfinal.modell.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
}
