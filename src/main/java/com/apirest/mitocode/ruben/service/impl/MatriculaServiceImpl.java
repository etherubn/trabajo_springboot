package com.apirest.mitocode.ruben.service.impl;

import com.apirest.mitocode.ruben.dto.request.MatriculaRequestDto;
import com.apirest.mitocode.ruben.dto.response.CursoEstudiantesDTO;
import com.apirest.mitocode.ruben.dto.response.DetalleMatriculaResponseDto;
import com.apirest.mitocode.ruben.dto.response.MatriculaResponseDto;
import com.apirest.mitocode.ruben.exception.BussinesException;
import com.apirest.mitocode.ruben.model.DetalleMatricula;
import com.apirest.mitocode.ruben.model.Estudiante;
import com.apirest.mitocode.ruben.model.Matricula;
import com.apirest.mitocode.ruben.repository.GenericRepo;
import com.apirest.mitocode.ruben.repository.ICursoRepo;
import com.apirest.mitocode.ruben.repository.IEstudianteRepo;
import com.apirest.mitocode.ruben.repository.IMatriculaRepo;
import com.apirest.mitocode.ruben.service.IMatriculaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MatriculaServiceImpl extends CRUDImpl<Matricula,Long> implements IMatriculaService {
    private final IMatriculaRepo matriculaRepo;
    private final ICursoRepo cursoRepo;
    private final IEstudianteRepo estudianteRepo;
    
    @Override
    protected GenericRepo getRepo() {
        return matriculaRepo;
    }

    @Override
    protected Class getEntityClass() {
        return Matricula.class;
    }

    @Override
    protected void setId(Matricula Matricula, Long d) {
        Matricula.setIdMatricula(d);
    }

    @Transactional
    public MatriculaResponseDto save(MatriculaRequestDto matriculaRequestDto){

        Estudiante estudiante = estudianteRepo.findById(
                matriculaRequestDto.getIdEstudiante()).orElseThrow(() ->
                new BussinesException("Estudiante no encontrado con ID: " + matriculaRequestDto.getIdEstudiante(), HttpStatus.NOT_FOUND));

        Matricula matricula = new Matricula();
        matricula.setEstudiante(estudiante);
        matricula.setFechaMatricula(matriculaRequestDto.getFechaMatricula());

        List<DetalleMatricula> detalleMatriculas = matriculaRequestDto.getDetalleMatricula().stream()
                .map(detalleMatriculaRequestDto -> {
                    // Validar si el estudiante ya está matriculado en el curso
                    boolean yaMatriculado = matriculaRepo.existsByEstudiante_IdEstudianteAndDetalleMatricula_Curso_IdCursoAndDetalleMatricula_Aula(
                            matriculaRequestDto.getIdEstudiante(), detalleMatriculaRequestDto.getIdCurso(), detalleMatriculaRequestDto.getAula());
                    if (yaMatriculado) {
                        throw new BussinesException("El estudiante ya está matriculado en el curso con ID: " + detalleMatriculaRequestDto.getIdCurso() + " en el aula: " + detalleMatriculaRequestDto.getAula(), HttpStatus.CONFLICT);
                    }

                    DetalleMatricula detalleMatricula = new DetalleMatricula();
                    detalleMatricula.setCurso(cursoRepo.findById(detalleMatriculaRequestDto.getIdCurso())
                            .orElseThrow(() -> new BussinesException("Curso no encontrado con ID: " + detalleMatriculaRequestDto.getIdCurso(), HttpStatus.NOT_FOUND)));
                    detalleMatricula.setAula(detalleMatriculaRequestDto.getAula());
                    detalleMatricula.setMatricula(matricula);
                    return detalleMatricula;
                }).collect(Collectors.toList());


        matricula.setDetalleMatricula(detalleMatriculas);

        Matricula savedMatricula = matriculaRepo.save(matricula);

        MatriculaResponseDto responseDto = new MatriculaResponseDto();
        responseDto.setIdMatricula(savedMatricula.getIdMatricula());
        responseDto.setFechaMatricula(savedMatricula.getFechaMatricula());
        responseDto.setIdEstudiante(savedMatricula.getEstudiante().getIdEstudiante());
        responseDto.setDetalleMatricula(savedMatricula.getDetalleMatricula().stream()
                .map(detalle -> new DetalleMatriculaResponseDto(
                        detalle.getIdDetail(),
                        detalle.getCurso().getIdCurso(),
                        detalle.getAula()
                )).collect(Collectors.toList()));

        return responseDto;
    }

    @Override
    public List<CursoEstudiantesDTO> obtenerCursosConEstudiantes() {
        // Obtener todas las matrículas desde la base de datos
        List<Matricula> matriculas = matriculaRepo.findAll();

        // Agrupar los estudiantes por curso
        Map<String, List<String>> cursosConEstudiantes = matriculas.stream()
                .flatMap(matricula -> matricula.getDetalleMatricula().stream())
                .collect(Collectors.groupingBy(
                        detalle -> detalle.getCurso().getNombre(),
                        Collectors.mapping(detalle -> detalle.getMatricula().getEstudiante().getNombres()+" / Aula: "+detalle.getAula(), Collectors.toList())
                ));

        // Convertir el Map a una lista de CursoEstudiantesDTO
        return cursosConEstudiantes.entrySet().stream()
                .map(entry -> new CursoEstudiantesDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

}
