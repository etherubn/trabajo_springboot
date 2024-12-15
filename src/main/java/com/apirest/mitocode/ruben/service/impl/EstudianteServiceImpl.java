package com.apirest.mitocode.ruben.service.impl;

import com.apirest.mitocode.ruben.exception.EntityAlreadyExistException;
import com.apirest.mitocode.ruben.exception.NotFoundException;
import com.apirest.mitocode.ruben.model.Estudiante;
import com.apirest.mitocode.ruben.repository.GenericRepo;
import com.apirest.mitocode.ruben.repository.IEstudianteRepo;
import com.apirest.mitocode.ruben.service.IEstudianteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstudianteServiceImpl extends CRUDImpl<Estudiante,Long> implements IEstudianteService {
    private final IEstudianteRepo estudianteRepo;
    
    @Override
    protected GenericRepo getRepo() {
        return estudianteRepo;
    }

    @Override
    protected Class getEntityClass() {
        return Estudiante.class;
    }

    @Override
    protected void setId(Estudiante Estudiante, Long d) {
        Estudiante.setIdEstudiante(d);
    }

    @Override
    public Estudiante create(Estudiante estudiante) {
        estudianteRepo.findByDni(estudiante.getDni()).ifPresent(estudiante1 -> {
                    throw new EntityAlreadyExistException("estudiante","dni");
                });

        return estudianteRepo.save(estudiante);
    }

    @Override
    public Estudiante update(Estudiante estudiante, Long ID) {
        estudianteRepo.findById(ID).orElseThrow(()->new NotFoundException("estudiante"));

        if (estudianteRepo.findByDniAndIdEstudianteIsNot(estudiante.getDni(), ID).isPresent()) {
            throw new EntityAlreadyExistException("estudiante","dni");
        }
        estudiante.setIdEstudiante(ID);
        return estudianteRepo.save(estudiante);
    }

    @Override
    public List<Estudiante> listarEstudidantePorEdadDescendente() {

        return estudianteRepo.findAll().stream()
                .sorted(Comparator.comparing((Estudiante e)-> e.getEdad()).reversed())
                .collect(Collectors.toList());
    }
}
