package com.apirest.mitocode.ruben.repository;

import com.apirest.mitocode.ruben.model.Curso;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICursoRepo extends GenericRepo<Curso,Long>{
    boolean existsByNombre(String nombre);
    Optional<Curso> findByNombreAndIdCursoIsNot(String nombre, Long id);
}
