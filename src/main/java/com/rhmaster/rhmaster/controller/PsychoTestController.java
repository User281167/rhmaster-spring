package com.rhmaster.rhmaster.controller;

import com.rhmaster.rhmaster.Services.PsychoTestService;
import com.rhmaster.rhmaster.dtos.PsychoTestRequestDto;
import com.rhmaster.rhmaster.dtos.PsychoTestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pruebas-psicologicas")
public class PsychoTestController {
    @Autowired
    private PsychoTestService psychoTestService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PSICO')")
    public ResponseEntity<String> save(@RequestBody PsychoTestRequestDto test) {
        return psychoTestService.save(test);
    }

    @PostMapping("/actualizar")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PSICO')")
    public ResponseEntity<String> update(@RequestBody PsychoTestDto test) {
        return psychoTestService.update(test);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PSICO')")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        return psychoTestService.delete(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PSICO')")
    public ResponseEntity<List<PsychoTestDto>> getAll() {
        return psychoTestService.getAll();
    }
}
