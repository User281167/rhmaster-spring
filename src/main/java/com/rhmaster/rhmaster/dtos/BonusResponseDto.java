package com.rhmaster.rhmaster.dtos;

import com.rhmaster.rhmaster.models.Bonus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BonusResponseDto {
    private UUID id;
    private Bonus bonus;
}
