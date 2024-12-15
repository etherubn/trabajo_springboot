package com.apirest.mitocode.ruben.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleMatriculaResponseDto {

    private Long idDetail;

    private Long idCurso;

    private String aula;

}
