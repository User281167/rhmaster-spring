package com.rhmaster.rhmaster.Services;

import com.rhmaster.rhmaster.dtos.PermissionRequestDto;
import com.rhmaster.rhmaster.dtos.PermissionResponseDto;
import com.rhmaster.rhmaster.models.Employee;
import com.rhmaster.rhmaster.models.Permission;
import com.rhmaster.rhmaster.repository.EmployeeRepository;
import com.rhmaster.rhmaster.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public void save(PermissionRequestDto permissionDto) {
        Employee employee = employeeRepository.findById(permissionDto.getEmployeeId()).get();

        String state = permissionDto.getState();

        if (state == null) {
            state = "PENDIENTE";
        }

        Permission permission = Permission.builder()
                        .type(permissionDto.getType())
                        .applicationDate(permissionDto.getApplicationDate())
                        .startDate(permissionDto.getStartDate())
                        .endDate(permissionDto.getEndDate())
                        .state(state)
                        .description(permissionDto.getDescription())
                        .adminResponse(permissionDto.getAdminResponse())
                        .employee(employee)
                        .build();

        if (permissionDto.getId() != null) {
            permission.setId(permissionDto.getId());
        }

        permissionRepository.save(permission);
    }

    public List<PermissionResponseDto> getPermissionByEmployeesId(UUID id) {
        List<Permission> permissions = permissionRepository.findAllByEmployeeId(id);
        return permissions.stream().map(PermissionResponseDto::new).toList();
    }

    public void deleteById(UUID id) {
        permissionRepository.deleteById(id);
    }
}
