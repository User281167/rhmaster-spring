package com.rhmaster.rhmaster.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequestDto {
    private UUID id;
    private String scheduleDate;
}
