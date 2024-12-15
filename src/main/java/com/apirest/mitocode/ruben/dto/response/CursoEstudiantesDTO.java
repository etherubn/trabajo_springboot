package com.apirest.mitocode.ruben.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CursoEstudiantesDTO {
    private String cursoNombre;
    private List<String> estudiantes;
}
