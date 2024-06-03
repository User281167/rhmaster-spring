package com.rhmaster.rhmaster.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rhmaster.rhmaster.models.Assignment;
import com.rhmaster.rhmaster.models.EmployeeAssignment;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssignmentDto {
    @NotBlank
    private UUID id;
    private String title;
    private String type;
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;

    private UUID fileId;

    public AssignmentDto(Assignment assignment) {
        this.id = assignment.getId();
        this.title = assignment.getTitle();
        this.type = assignment.getType();
        this.description = assignment.getDescription();
        this.date = assignment.getDate();
        this.fileId = assignment.getFile() != null ? assignment.getFile().getId() : null;
    }

    public AssignmentDto(EmployeeAssignment employeeAssignment) {
        this.id = employeeAssignment.getAssignment().getId();
        this.title = employeeAssignment.getAssignment().getTitle();
        this.type = employeeAssignment.getAssignment().getType();
        this.description = employeeAssignment.getAssignment().getDescription();
        this.date = employeeAssignment.getAssignment().getDate();
        this.fileId = employeeAssignment.getAssignment().getFile() != null ? employeeAssignment.getAssignment().getFile().getId() : null;
    }
}
