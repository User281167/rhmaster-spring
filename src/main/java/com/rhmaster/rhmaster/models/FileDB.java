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
@Builder
@Table(name = "archivos")
public class FileDB {
    @Id
    @GeneratedValue
    private UUID id;

    @Lob
    private byte[] data;

    @Column(name = "nombre")
    private String name;

    @Column(name = "tipo_archivo")
    private String fileType;

    @Column(name = "tipo")
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
