package com.rhmaster.rhmaster.controller;

import com.rhmaster.rhmaster.Services.PermissionService;
import com.rhmaster.rhmaster.dtos.PermissionRequestDto;
import com.rhmaster.rhmaster.dtos.PermissionResponseDto;
import com.rhmaster.rhmaster.models.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/permisos")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_CONTRATADO') or hasRole('ROLE_ADMIN')")
    public void save(@RequestBody PermissionRequestDto permission) {
        permissionService.save(permission);
    }

    @GetMapping("/{employeeId}")
    @PreAuthorize("hasRole('ROLE_CONTRATADO') or hasRole('ROLE_ADMIN')")
    public List<PermissionResponseDto> getPermission(@PathVariable("employeeId") UUID employeeId) {
        return permissionService.getPermissionByEmployeesId(employeeId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(@PathVariable("id") UUID id) {
        permissionService.deleteById(id);
    }
}
