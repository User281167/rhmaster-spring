package com.rhmaster.rhmaster.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rhmaster.rhmaster.models.ExtraHour;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtraHourDto {
    private UUID id;
    private Integer hours;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;

    @NotBlank
    private UUID scheduleId;

    public ExtraHourDto(ExtraHour extraHour) {
        this.id = extraHour.getId();
        this.hours = extraHour.getHours();
        this.date = extraHour.getDate();
        this.scheduleId = extraHour.getSchedule().getId();
    }
}
