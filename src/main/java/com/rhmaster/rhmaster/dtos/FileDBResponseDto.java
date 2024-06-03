package com.rhmaster.rhmaster.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDBResponseDto {
    private UUID id;
    private String name;
    private String fileType;
    private String type;
}
