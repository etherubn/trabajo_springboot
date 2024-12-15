package com.apirest.mitocode.ruben.dto.request;

import jakarta.validation.Valid;
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
public class MatriculaRequestDto {



    private Long idEstudiante;

    private LocalDateTime fechaMatricula;

    @NotNull
    @Valid
    private List<DetalleMatriculaRequestDto> detalleMatricula;

}
