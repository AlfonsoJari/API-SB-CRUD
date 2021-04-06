package com.webservice.api.repository;
import com.webservice.api.model.Empleados;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadosRepository extends JpaRepository<Empleados, Long> {

}
