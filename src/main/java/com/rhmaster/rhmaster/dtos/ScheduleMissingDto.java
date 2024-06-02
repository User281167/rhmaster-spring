package com.rhmaster.rhmaster.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rhmaster.rhmaster.models.ScheduleMissing;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleMissingDto {
    private UUID id;
    private String reason;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;

    @NotBlank
    private UUID scheduleId;

    public ScheduleMissingDto(ScheduleMissing missing) {
        this.id = missing.getId();
        this.reason = missing.getReason();
        this.date = missing.getDate();
        this.scheduleId = missing.getSchedule().getId();
    }
}
