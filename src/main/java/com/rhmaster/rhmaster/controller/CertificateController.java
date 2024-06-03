package com.rhmaster.rhmaster.controller;

import com.rhmaster.rhmaster.Services.CertificateService;
import com.rhmaster.rhmaster.dtos.CertificateDto;
import com.rhmaster.rhmaster.dtos.CertificateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/certificados")
public class CertificateController {
    @Autowired
    private CertificateService certificateService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CONTRATADO')")
    public ResponseEntity<String> save(@RequestBody CertificateRequestDto certificatetDto) {
        return certificateService.save(certificatetDto);
    }

    @PostMapping("/actualizar")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> update(@RequestBody CertificateDto certificateDto) {
        return certificateService.update(certificateDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CANDIDATO')")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        return certificateService.delete(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<CertificateDto>> getAll() {
        return certificateService.getAll();
    }

    @GetMapping("/{employeeId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CONTRATADO')")
    public ResponseEntity<List<CertificateDto>> getByEmployee(@PathVariable("employeeId") UUID employeeId) {
        return certificateService.findByEmployeeId(employeeId);
    }
}
