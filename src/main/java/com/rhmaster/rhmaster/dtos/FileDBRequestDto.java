package com.rhmaster.rhmaster.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDBRequestDto {
    private UUID id;
    private String name;
    private String type;
}
