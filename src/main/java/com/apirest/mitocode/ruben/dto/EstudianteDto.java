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
public class EstudianteDto {
    @JsonProperty("id")
    private Long idEstudiante;

    @NotBlank
    @Size(max = 30)
    private String nombres;

    @NotBlank
    @Size(max = 30)
    private String apellidos;

    @Size(min = 8,max = 8)
    private String dni;

    @Positive
    private int edad;



}
