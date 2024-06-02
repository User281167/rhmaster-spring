package com.rhmaster.rhmaster.controller;

import com.rhmaster.rhmaster.Services.UserServiceImpl;
import com.rhmaster.rhmaster.exceptions.RoleNotFoundException;
import com.rhmaster.rhmaster.models.User;
import com.rhmaster.rhmaster.dtos.SetRoleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/cambiar-rol")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> setRole(@RequestBody SetRoleRequest request) {
        try {
            userService.setRole(request.getUserId(), request.getRole());
            return ResponseEntity.ok("Rol cambiado correctamente");
        } catch (RoleNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/todos")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getUsers() {
        return userService.getAllUser();
    }
}
