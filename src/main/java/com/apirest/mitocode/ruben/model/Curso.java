package com.apirest.mitocode.ruben.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idCurso;

    @Column(length = 50,nullable = false,unique = true)
    private String nombre;

    @Column(length = 25,nullable = false)
    private String siglas;

    @Column(nullable = false)
    private boolean estado;


}
