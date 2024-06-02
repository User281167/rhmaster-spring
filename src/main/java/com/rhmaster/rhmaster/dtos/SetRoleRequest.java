package com.rhmaster.rhmaster.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetRoleRequest {
    @NotBlank
    private UUID userId;

    @NotBlank
    private String role;
}
