package com.apirest.mitocode.ruben.controller;


import com.apirest.mitocode.ruben.dto.CursoDto;
import com.apirest.mitocode.ruben.dto.GenericResponse;
import com.apirest.mitocode.ruben.model.Curso;
import com.apirest.mitocode.ruben.service.ICursoService;
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
@RequestMapping("/api/v1/curso")
public class CursoController {

    private final ICursoService cursoService;
    private final MapperUtil mapperUtil;


    @GetMapping
    public ResponseEntity<GenericResponse<CursoDto>> getAll(){
        List<CursoDto> cursoDtos = mapperUtil.map(cursoService.getAll(), CursoDto.class);

        return new ResponseEntity<>(new GenericResponse<>(200,"success",cursoDtos), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<CursoDto>> getById(@PathVariable Long id){
        CursoDto cursoDto = mapperUtil.map(cursoService.getById(id), CursoDto.class);
        return new ResponseEntity<>(new GenericResponse<>(200,"success",Arrays.asList(cursoDto)),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        cursoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GenericResponse<CursoDto>> create(@RequestBody @Valid CursoDto cursoDto) {
        Curso curso = cursoService.create(mapperUtil.map(cursoDto, Curso.class));
        CursoDto cursoDto1 = mapperUtil.map(curso, CursoDto.class);
        return new ResponseEntity<>(new GenericResponse<>(200,"succes", Arrays.asList(cursoDto1)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<CursoDto>> update(@RequestBody @Valid CursoDto cursoDto, @PathVariable Long id) {
        Curso curso = cursoService.update(mapperUtil.map(cursoDto, Curso.class),id);
        CursoDto cursoDto1 = mapperUtil.map(curso, CursoDto.class);
        return new ResponseEntity<>(new GenericResponse<>(200,"succes", Arrays.asList(cursoDto1)), HttpStatus.CREATED);
    }



}
