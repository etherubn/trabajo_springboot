package com.apirest.mitocode.ruben.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoDto {

    @JsonProperty("id")
    private Long idCurso;

    @NotBlank
    @Size
    private String nombre;

    @NotBlank
    @Size
    private String siglas;

    @NotNull
    private boolean estado;



}
