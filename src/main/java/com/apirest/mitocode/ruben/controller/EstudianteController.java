package com.apirest.mitocode.ruben.controller;


import com.apirest.mitocode.ruben.dto.EstudianteDto;
import com.apirest.mitocode.ruben.dto.GenericResponse;
import com.apirest.mitocode.ruben.model.Estudiante;
import com.apirest.mitocode.ruben.service.IEstudianteService;
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
@RequestMapping("/api/v1/estudiante")
public class EstudianteController {

    private final IEstudianteService estudianteService;
    private final MapperUtil mapperUtil;


    @GetMapping
    public ResponseEntity<GenericResponse<EstudianteDto>> getAll(){
        List<EstudianteDto> estudianteDtos = mapperUtil.map(estudianteService.getAll(), EstudianteDto.class);

        return new ResponseEntity<>(new GenericResponse<>(200,"success",estudianteDtos), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<EstudianteDto>> getById(@PathVariable Long id){
        EstudianteDto estudianteDto = mapperUtil.map(estudianteService.getById(id), EstudianteDto.class);
        return new ResponseEntity<>(new GenericResponse<>(200,"success",Arrays.asList(estudianteDto)),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        estudianteService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GenericResponse<EstudianteDto>> create(@RequestBody @Valid EstudianteDto estudianteDto) {
        Estudiante estudiante = estudianteService.create(mapperUtil.map(estudianteDto, Estudiante.class));
        EstudianteDto estudianteDto1 = mapperUtil.map(estudiante, EstudianteDto.class);
        return new ResponseEntity<>(new GenericResponse<>(200,"succes", Arrays.asList(estudianteDto1)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<EstudianteDto>> update(@RequestBody @Valid EstudianteDto estudianteDto, @PathVariable Long id) {
        Estudiante estudiante = estudianteService.update(mapperUtil.map(estudianteDto, Estudiante.class),id);
        EstudianteDto estudianteDto1 = mapperUtil.map(estudiante, EstudianteDto.class);
        return new ResponseEntity<>(new GenericResponse<>(200,"succes", Arrays.asList(estudianteDto1)), HttpStatus.CREATED);
    }

    @GetMapping("order-by/edad/desc")
    public ResponseEntity<GenericResponse<List<EstudianteDto>>> getOrderByEdadDesc(){
        List<EstudianteDto> estudianteDtos = mapperUtil.map(estudianteService.listarEstudidantePorEdadDescendente(), EstudianteDto.class);
        return new ResponseEntity<>(new GenericResponse<>(200,"success",Arrays.asList(estudianteDtos)), HttpStatus.OK);
    }

}
