package com.rhmaster.rhmaster.controller;

import com.rhmaster.rhmaster.Services.AssignmentService;
import com.rhmaster.rhmaster.Services.EmployeeAssignmentService;
import com.rhmaster.rhmaster.dtos.*;
import com.rhmaster.rhmaster.models.EmployeeAssignment;
import com.rhmaster.rhmaster.repository.EmployeeAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/asignaciones")
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private EmployeeAssignmentService employeeAssignmentService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> save(@RequestBody AssignmentRequestDto assignmentRequestDto) {
        return assignmentService.save(assignmentRequestDto);
    }

    @PostMapping("/actualizar")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> update(@RequestBody AssignmentDto assignmentDto) {
        return assignmentService.update(assignmentDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        return assignmentService.delete(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<AssignmentDto>> getAll() {
        return assignmentService.getAll();
    }

    @GetMapping("/{type}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<AssignmentDto>> getByTipe(@PathVariable("type") String type) {
        return assignmentService.findType(type);
    }

    @PostMapping("/asignar")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> assign(@RequestBody EmployeeAssignmentRequestDto requestDto) {
        return employeeAssignmentService.save(requestDto);
    }

    @GetMapping("/todos")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<EmployeeAssignmentResponseDto> getAllAssignments() {
        return  employeeAssignmentService.getAll();
    }

    @DeleteMapping("/remover-asignacion/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteById(@PathVariable("id") UUID id) {
        return employeeAssignmentService.deleteById(id);
    }

    @GetMapping("/usuarios/{assignmentId}/{employeeId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CANDIDATO') or hasRole('ROLE_RETIRADO') or hasRole('ROLE_EVALUADO') or hasRole('ROLE_CONTRATADO')")
    public List<EmployeeAssignmentResponseDto> getAllByEmployeeId(@PathVariable("assignmentId") UUID assignmentId, @PathVariable("employeeId") UUID employeeId) {
        return employeeAssignmentService.getAllByEmployeeId(assignmentId, employeeId);
    }
}
