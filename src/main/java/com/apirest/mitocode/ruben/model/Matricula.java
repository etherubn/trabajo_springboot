package com.apirest.mitocode.ruben.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Builder
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idMatricula;

    @Column(nullable = false)
    private LocalDateTime fechaMatricula;

    @PrePersist
    public void prePersist() {
        fechaMatricula = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false,foreignKey = @ForeignKey(name = "FK_Matricula_Estudiante"))
    private Estudiante estudiante;

    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleMatricula> detalleMatricula;

    private boolean estado;

}
