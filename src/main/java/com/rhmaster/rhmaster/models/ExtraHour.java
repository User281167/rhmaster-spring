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
@Table(name = "horario_extra")
@Builder
public class ExtraHour {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "horas")
    private Integer hours;

    @Column(name = "fecha")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Date date;

    @ManyToOne
    @JoinColumn(name = "horario_id", referencedColumnName = "id")
    Schedule schedule;
}
