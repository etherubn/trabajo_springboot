package com.apirest.mitocode.ruben.service;



import com.apirest.mitocode.ruben.dto.request.MatriculaRequestDto;
import com.apirest.mitocode.ruben.dto.response.CursoEstudiantesDTO;
import com.apirest.mitocode.ruben.dto.response.MatriculaResponseDto;
import com.apirest.mitocode.ruben.model.Matricula;

import java.util.List;

public interface IMatriculaService extends ICRUD<Matricula,Long>{
    MatriculaResponseDto save(MatriculaRequestDto matriculaDto);
    List<CursoEstudiantesDTO> obtenerCursosConEstudiantes();
}
