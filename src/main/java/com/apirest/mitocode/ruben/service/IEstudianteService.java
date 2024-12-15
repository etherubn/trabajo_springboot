package com.apirest.mitocode.ruben.service;


import com.apirest.mitocode.ruben.model.Estudiante;

import java.util.List;

public interface IEstudianteService extends ICRUD<Estudiante,Long>{
    List<Estudiante> listarEstudidantePorEdadDescendente();
}
