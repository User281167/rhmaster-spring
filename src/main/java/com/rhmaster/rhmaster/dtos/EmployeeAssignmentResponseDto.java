package com.rhmaster.rhmaster.dtos;

import com.rhmaster.rhmaster.models.Assignment;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAssignmentResponseDto {
    private UUID id = null;
    private UUID employeeId;
    private AssignmentDto assignment;
}
