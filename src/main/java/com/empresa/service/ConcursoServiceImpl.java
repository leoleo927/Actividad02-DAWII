package com.empresa.service;

import com.empresa.entity.Concurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.empresa.repository.ConcursoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ConcursoServiceImpl implements ConcursoService{

    @Autowired
    private ConcursoRepository repository;

    @Override
    public List<Concurso> lstConcurso() {
        return repository.findAll();
    }

    @Override
    public List<Concurso> lstConcursoxNombre(String nombre) {
        return repository.lstConcursoxNombre(nombre);
    }


    public List<Concurso> lstConcursoxIdDifDelMismo(String nombre, int id) {
        return repository.lstConcursoxIdDifDelMismo(nombre, id);
    }

    @Override
    public Concurso insertandupdtConcurso(Concurso obj) {
        return repository.save(obj);
    }

    @Override
    public Optional<Concurso> buscaxId(int id) {
        return repository.findById(id);
    }

    @Override
    public void dltxId(int id) {
        repository.deleteById(id);
    }


}
