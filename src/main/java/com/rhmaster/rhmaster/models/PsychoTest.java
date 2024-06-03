package com.rhmaster.rhmaster.models;

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
@Builder
@Table(name = "pruebas_psico")
public class PsychoTest {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "notas")
    private String notes;

    @Column(name = "fecha")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "archivo_id", referencedColumnName = "id")
    private FileDB file;
}
