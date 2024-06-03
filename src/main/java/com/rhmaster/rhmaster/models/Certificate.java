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
@Table(name = "certificados")
@Builder
public class Certificate {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "tipo")
    private String type;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "fecha_solicitud")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;

    @Column(name = "pendiente")
    private boolean isPending;

    @Column(name = "respuesta_admin")
    private String adminResponse;

    @ManyToOne
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "archivo_id", referencedColumnName = "id")
    private FileDB file;
}
