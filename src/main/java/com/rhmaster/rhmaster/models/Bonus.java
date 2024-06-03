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
@Table(name = "bonificaciones")
@Builder
public class Bonus {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "valor")
    private Integer value;

    @Column(name = "tipo")
    private String type;

    @Column(name = "es_auxilio")
    private Boolean isAuxiliary;

    @Column(name = "es_deducido")
    private Boolean isDeducted;
}
