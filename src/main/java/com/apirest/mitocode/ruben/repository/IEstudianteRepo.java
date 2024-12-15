package com.apirest.mitocode.ruben.repository;

import com.apirest.mitocode.ruben.model.Estudiante;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEstudianteRepo extends GenericRepo<Estudiante,Long>{
    Optional<Estudiante> findByDni(String dni);


    Optional<Estudiante> findByDniAndIdEstudianteIsNot(String dni, Long idEstudiante);
}
