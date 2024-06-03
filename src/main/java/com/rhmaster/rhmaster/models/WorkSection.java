package com.rhmaster.rhmaster.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "secciones")
public class WorkSection {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "nombre")
    private String name;
}
