package com.rhmaster.rhmaster.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "horario_falta")
@Builder
public class ScheduleMissing {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "razon")
    String reason;

    @Column(name = "fecha")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Date date;

    @ManyToOne
    @JoinColumn(name = "horario_id", referencedColumnName = "id")
    Schedule schedule;
}
