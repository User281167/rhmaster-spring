package com.rhmaster.rhmaster.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "asignacion_empleado")
@Builder
public class EmployeeAssignment {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "notas")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "asignacion_id", referencedColumnName = "id")
    private Assignment assignment;
}
