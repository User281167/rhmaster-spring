package com.rhmaster.rhmaster.requestClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetRoleRequest {
    private UUID userId;
    private String role;
}
