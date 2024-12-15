package com.apirest.mitocode.ruben.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idEstudiante;

    @Column(length = 30, nullable = false)
    private String nombres;

    @Column(length = 30, nullable = false)
    private String apellidos;

    @Column(length = 8, unique = true, nullable = false)
    private String dni;

    @Column(length = 20, nullable = false)
    private int edad;

}
