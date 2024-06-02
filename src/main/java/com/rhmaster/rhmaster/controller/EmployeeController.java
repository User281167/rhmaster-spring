package com.rhmaster.rhmaster.controller;

import com.rhmaster.rhmaster.Services.EmployeeService;
import com.rhmaster.rhmaster.dtos.EmployeeDto;
import com.rhmaster.rhmaster.dtos.SetEmployeeDto;
import com.rhmaster.rhmaster.dtos.EmployeeRequestDto;
import com.rhmaster.rhmaster.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/empleados")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/todos")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<EmployeeDto> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CONTRATADO')")
    public EmployeeDto getEmployeeById(@PathVariable("id") UUID id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void save(@RequestBody EmployeeRequestDto employee) {
        employeeService.saveEmployee(employee);
    }

    @PostMapping("/actualizar")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CONTRATADO')")
    public ResponseEntity<String> update(@RequestBody SetEmployeeDto employeeDto) {
        return employeeService.update(employeeDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(@PathVariable("id") UUID id) {
        employeeService.deleteEmployeeById(id);
    }
}
