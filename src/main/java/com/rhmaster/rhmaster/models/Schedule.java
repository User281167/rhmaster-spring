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
@Table(name = "horarios")
@Builder
public class Schedule {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "horario")
    private String scheduleDate;

    @OneToOne
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    private Employee employee;
}
