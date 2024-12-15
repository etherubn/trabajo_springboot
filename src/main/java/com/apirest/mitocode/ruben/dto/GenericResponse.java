package com.apirest.mitocode.ruben.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class GenericResponse<T> {
    private int status;
    private String message;
    private List<T> list;
}
