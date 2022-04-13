package com.empresa.repository;

import com.empresa.entity.Concurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConcursoRepository extends JpaRepository <Concurso, Integer> {

    @Query("Select x from Concurso x where x.nombre = ?1")
    List <Concurso> lstConcursoxNombre(String nombre);

    @Query("Select x from Concurso x where x.nombre = ?1 and x.id <> ?2")
    List<Concurso>lstConcursoxIdDifDelMismo(String name, int id);

}
