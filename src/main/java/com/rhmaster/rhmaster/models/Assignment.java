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
@Table(name = "asignaciones")
@Builder
public class Assignment {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "titulo")
    private String title;

    @Column(name = "tipo")
    private String type;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "fecha_creacion")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date date;

    @OneToOne
    @JoinColumn(name = "archivo_id", referencedColumnName = "id")
    private FileDB file;
}
