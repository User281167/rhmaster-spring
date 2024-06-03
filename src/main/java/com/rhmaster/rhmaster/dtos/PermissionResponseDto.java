package com.rhmaster.rhmaster.dtos;

import com.rhmaster.rhmaster.models.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResponseDto {
    private UUID id;
    private String type;
    private Date applicationDate;
    private Date startDate;
    private Date endDate;
    private String state;
    private String description;
    private String adminResponse;
    private UUID employeeId;

    public PermissionResponseDto(Permission permission) {
        this.id = permission.getId();
        this.type = permission.getType();
        this.applicationDate = permission.getApplicationDate();
        this.startDate = permission.getStartDate();
        this.endDate = permission.getEndDate();
        this.state = permission.getState();
        this.description = permission.getDescription();
        this.adminResponse = permission.getAdminResponse();
        this.employeeId = permission.getEmployee().getId();
    }
}
