package com.empresa.service;

import com.empresa.entity.Concurso;

import java.util.List;
import java.util.Optional;

public interface ConcursoService {

    List<Concurso> lstConcurso();

    List<Concurso> lstConcursoxNombre(String nombre);

    Concurso insertandupdtConcurso(Concurso obj);

    Optional<Concurso> buscaxId(int id);

    List<Concurso> lstConcursoxIdDifDelMismo(String name, int id);

    void dltxId(int id);

}
