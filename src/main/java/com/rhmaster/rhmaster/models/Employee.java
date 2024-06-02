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
@Table(name = "empleados")
@Builder
public class Employee {
    @Id
    private UUID id;

    @Column(name =  "direccion")
    private String adress;

    @Column(name =  "cedula")
    private String cedula;

    @Column(name =  "telefono")
    private String phone;

    @Column(name =  "fecha_union")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date unionDate;

    @Column(name =  "fecha_ultimo_dia")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date lastDayDate;

    @Column(name =  "fecha_retiro")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date retirementDate;

    @Column(name =  "cargo")
    private String post;

    @Column(name =  "salario")
    private Integer salary;

    @Column(name =  "habilidades")
    private String skills;

    @Column(name =  "entidad_bancaria")
    private String bankEntity;

    @Column(name =  "cuenta_bancaria")
    private String bankAccount;

    @OneToOne
    @JoinColumn(name = "seccion", referencedColumnName = "id")
    private WorkSection workSection;
}
