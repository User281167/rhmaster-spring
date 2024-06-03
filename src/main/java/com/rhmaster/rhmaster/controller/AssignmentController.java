package com.rhmaster.rhmaster.controller;

import com.rhmaster.rhmaster.Services.AssignmentService;
import com.rhmaster.rhmaster.dtos.AssignmentDto;
import com.rhmaster.rhmaster.dtos.AssignmentRequestDto;
import com.rhmaster.rhmaster.dtos.CertificateDto;
import com.rhmaster.rhmaster.dtos.CertificateRequestDto;
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
}
