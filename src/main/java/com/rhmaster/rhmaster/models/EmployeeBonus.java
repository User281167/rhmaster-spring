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
@Table(name = "bonificacion_empleado")
@Builder
public class EmployeeBonus {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "bonificacion_id", referencedColumnName = "id")
    private Bonus bonus;
}
