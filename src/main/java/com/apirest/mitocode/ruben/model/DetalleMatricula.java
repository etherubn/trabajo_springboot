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
public class DetalleMatricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idDetail;

    @ManyToOne
    @JoinColumn(name = "id_matricula", nullable = false, foreignKey = @ForeignKey(name = "FK_DETAIL_MATRICULA"))
    private Matricula matricula;

    @ManyToOne
    @JoinColumn(name= "id_curso" ,nullable = false, foreignKey = @ForeignKey(name = "FK_DETAIL_CURSO"))
    private Curso curso;

    @Size(max = 20)
    @Column(nullable = false)
    private String aula;

}
