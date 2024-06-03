package com.rhmaster.rhmaster.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BonusRequestDto {
    private UUID id =  null;

    @NotBlank
    private UUID employeeId;

    @NotBlank
    private UUID bonusId;

    @NotBlank
    private boolean isAuxiliary;
}
