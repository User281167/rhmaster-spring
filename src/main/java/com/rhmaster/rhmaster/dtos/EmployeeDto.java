package com.rhmaster.rhmaster.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rhmaster.rhmaster.models.WorkSection;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    @NotBlank
    private UUID id;

    private String adress;
    private String cedula;
    private String phone;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date unionDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date lastDayDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date retirementDate;

    private String post;
    private Integer salary;
    private String skills;
    private String bankEntity;
    private String bankAccount;
    private WorkSection workSection;
    private UserDto user;
}
