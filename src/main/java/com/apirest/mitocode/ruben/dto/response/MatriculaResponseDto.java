package com.apirest.mitocode.ruben.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaResponseDto {

    private Long idMatricula;


    private Long idEstudiante;


    private LocalDateTime fechaMatricula;


    private List<DetalleMatriculaResponseDto> detalleMatricula;

}
