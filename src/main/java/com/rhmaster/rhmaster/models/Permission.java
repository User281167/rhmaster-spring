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
@Table(name = "permisos")
@Builder
public class Permission {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "tipo")
    private String type;

    @Column(name = "fecha_solicitud")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date applicationDate;

    @Column(name = "fecha_inicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date startDate;

    @Column(name = "fecha_fin")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date endDate;

    @Column(name = "estado")
    private String state;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "respuesta_admin")
    private String adminResponse;

    @ManyToOne
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    private Employee employee;
}
