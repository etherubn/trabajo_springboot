package com.apirest.mitocode.ruben.controller;

import com.apirest.mitocode.ruben.dto.EstudianteDto;
import com.apirest.mitocode.ruben.dto.GenericResponse;
import com.apirest.mitocode.ruben.dto.request.MatriculaRequestDto;
import com.apirest.mitocode.ruben.dto.response.CursoEstudiantesDTO;
import com.apirest.mitocode.ruben.dto.response.MatriculaResponseDto;
import com.apirest.mitocode.ruben.service.IMatriculaService;
import com.apirest.mitocode.ruben.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/matricula")
public class MatriculaController {

    private final IMatriculaService matriculaService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<MatriculaResponseDto>> findAll() {
        List<MatriculaResponseDto> list = mapperUtil.map(matriculaService.getAll(), MatriculaResponseDto.class);

        return ResponseEntity.ok(list);
    }

    @PostMapping
    public  ResponseEntity<MatriculaResponseDto> save(@RequestBody @Valid MatriculaRequestDto matriculaDto) {

        MatriculaResponseDto matriculaResponseDto = matriculaService.save(matriculaDto);

        return new ResponseEntity<>(matriculaResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/order-by")
    public ResponseEntity<List<CursoEstudiantesDTO>> getEstudiantesByCurso(){
        List<CursoEstudiantesDTO> list = matriculaService.obtenerCursosConEstudiantes();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
