package com.apirest.mitocode.ruben.repository;


import com.apirest.mitocode.ruben.model.Matricula;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Repository;

@Repository
public interface IMatriculaRepo extends GenericRepo<Matricula,Long>{
    boolean existsByEstudiante_IdEstudianteAndDetalleMatricula_Curso_IdCursoAndDetalleMatricula_Aula(Long estudianteId, Long cursoId,String aula);
}
