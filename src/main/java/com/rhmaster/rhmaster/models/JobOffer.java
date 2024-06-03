package com.rhmaster.rhmaster.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "ofertas")
public class JobOffer {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "titulo")
    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "fecha_publicacion")
    private Date published;

    @Column(name = "visible")
    private boolean visible;

    @Column(name = "cargo")
    private String post;

    @Column(name = "experiencia")
    private String experience;

    @Column(name = "tipo")
    private String type;

    @Column(name = "lugar")
    private String place;

    @Column(name = "salario")
    private int salary;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "responsabilidades")
    private String responsabilities;

    @Column(name = "requisitos")
    private String requirements;

    @Column(name = "prestaciones")
    private String benefits;
}
