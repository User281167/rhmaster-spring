package com.rhmaster.rhmaster.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetEmployeeDto {
    @NotBlank
    private UUID employeeId;

    private String address;
    private String phone;
    private String skills;
}
