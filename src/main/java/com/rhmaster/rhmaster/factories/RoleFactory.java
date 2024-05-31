package com.rhmaster.rhmaster.factories;

import com.rhmaster.rhmaster.exceptions.RoleNotFoundException;
import com.rhmaster.rhmaster.models.ERole;
import com.rhmaster.rhmaster.models.Role;
import com.rhmaster.rhmaster.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleFactory {
    @Autowired
    RoleRepository roleRepository;

    public Role getInstance(String role) throws RoleNotFoundException {
        switch (role) {
            case "admin" -> {
                return roleRepository.findByName(ERole.ROLE_ADMIN);
            }
            case "super_admin" -> {
                return roleRepository.findByName(ERole.ROLE_SUPER_ADMIN);
            }
            case "candidato" -> {
                return roleRepository.findByName(ERole.ROLE_CANDIDATO);
            }
            case "evaluado" -> {
                return roleRepository.findByName(ERole.ROLE_EVALUADO);
            }
            case "contratado" -> {
                return roleRepository.findByName(ERole.ROLE_CONTRATADO);
            }
            case "retirado" -> {
                return roleRepository.findByName(ERole.ROLE_RETIRADO);
            }
            case "psico" -> {
                return roleRepository.findByName(ERole.ROLE_PSICO);
            }
            default -> throw new RoleNotFoundException("No role found for " + role);
        }
    }

}
