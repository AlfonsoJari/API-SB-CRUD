package com.webservice.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.webservice.api.repository.EmpleadosRepository;
import com.webservice.api.exception.ResourceNotFoundException;
import com.webservice.api.model.Empleados;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EmpleadosController {

    @Autowired
    private EmpleadosRepository empleadosRepository;

    @GetMapping("/empleados")
    public List<Empleados> getAllEmpleados() {
        return empleadosRepository.findAll();
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleados> getPesosById(@PathVariable(value = "id") Long empleadoId) throws ResourceNotFoundException {
        Empleados empleados = empleadosRepository.findById(empleadoId)
        .orElseThrow(() -> new ResourceNotFoundException("Empleados not found for this id :: " + empleadoId));
        return ResponseEntity.ok().body(empleados);
    }

    @PostMapping("/empleados")
    public Empleados createEmpleados(@Validated @RequestBody Empleados empleados) {
        return empleadosRepository.save(empleados);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleados> updateEmpleados(@PathVariable(value = "id") Long empleadosId, @RequestBody Empleados empleadosDetails) throws ResourceNotFoundException {
        Empleados empleados = empleadosRepository.findById(empleadosId)
                .orElseThrow(() -> new ResourceNotFoundException("Empleados not found for this id :: " + empleadosId));

        empleados.setId(empleadosDetails.getId());
        empleados.setNombre(empleadosDetails.getNombre());
        empleados.setDireccion(empleadosDetails.getDireccion());
        empleados.setTelefono(empleadosDetails.getTelefono());
        final Empleados updatedEmpleados = empleadosRepository.save(empleados);
        return ResponseEntity.ok(updatedEmpleados);
    }

    @DeleteMapping("/empleados/{id}")
    public Map<String, Boolean> deleteEmpleados(@PathVariable(value = "id") Long empleadosId) throws ResourceNotFoundException {
        empleadosRepository.deleteById(empleadosId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
