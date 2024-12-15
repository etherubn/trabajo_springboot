package com.apirest.mitocode.ruben.service.impl;

import com.apirest.mitocode.ruben.exception.EntityAlreadyExistException;
import com.apirest.mitocode.ruben.exception.NotFoundException;
import com.apirest.mitocode.ruben.model.Curso;
import com.apirest.mitocode.ruben.repository.GenericRepo;
import com.apirest.mitocode.ruben.repository.ICursoRepo;
import com.apirest.mitocode.ruben.service.ICursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl extends CRUDImpl<Curso,Long> implements ICursoService {
    private final ICursoRepo cursoRepo;

    @Override
    protected GenericRepo getRepo() {
        return cursoRepo;
    }

    @Override
    protected Class getEntityClass() {
        return Curso.class;
    }

    @Override
    protected void setId(Curso curso, Long d) {
        curso.setIdCurso(d);
    }

    @Override
    public Curso create(Curso curso) {
        boolean exists = cursoRepo.existsByNombre(curso.getNombre());

        if (exists) {
            throw new EntityAlreadyExistException("curso","nombre");
        }

        return cursoRepo.save(curso);
    }

    @Override
    public Curso update(Curso curso, Long ID) {
        cursoRepo.findById(ID).orElseThrow(() -> new NotFoundException("curso"));

        if (cursoRepo.findByNombreAndIdCursoIsNot(curso.getNombre(), ID).isPresent()) {
            throw new EntityAlreadyExistException("curso", "nombre");
        }
        curso.setIdCurso(ID);
        return cursoRepo.save(curso);
    }
}
