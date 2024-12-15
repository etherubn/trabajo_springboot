package com.apirest.mitocode.ruben.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleMatriculaRequestDto {

    private Long idCurso;


    @NotBlank
    @Pattern(regexp = "\\S+", message = "El aula no debe contener espacios en blanco")
    private String aula;

}
