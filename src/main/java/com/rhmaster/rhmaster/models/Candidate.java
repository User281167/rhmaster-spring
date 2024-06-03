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
@Table(name = "candidatos")
@Builder
public class Candidate {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "preliminar")
    private boolean preliminary;

    @OneToOne
    @JoinColumn(name = "oferta_id", referencedColumnName = "id")
    private JobOffer jobOffer;

    @ManyToOne
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    private Employee employee;
}
