package com.empresa.controller;

import com.empresa.entity.Concurso;
import com.empresa.service.ConcursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/concurso")
public class ConcursoController {

    @Autowired
    private ConcursoService service;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Concurso>> lstConcurso() {
        List<Concurso> lista = service.lstConcurso();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> insertandupdtConcurso(@RequestBody Concurso obj){
        HashMap<String, Object> salida = new HashMap<>();

        try {
            List<Concurso> lista = service.lstConcursoxNombre(obj.getNombre());
            if (CollectionUtils.isEmpty(lista)) {
                Concurso objSalida = service.insertandupdtConcurso(obj);
                if (objSalida == null) {
                    salida.put("mensaje", "Error en el registro ");
                } else {
                    salida.put("mensaje", "Registro exitoso con el ID:  " + obj.getId());
                }
            }else{
                salida.put("mensaje", "El concurso ya existe: " + obj.getNombre());
            }
        } catch (Exception e){
            e.printStackTrace();
            salida.put("mensaje", "Error en el registro " + e.getMessage());
        }

        return ResponseEntity.ok(salida);
    }

    @PutMapping
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> actualizaConcurso(@RequestBody Concurso obj){
        HashMap<String, Object> salida = new HashMap<>();

        try {
            Optional<Concurso> optional = service.buscaxId(obj.getId());
            if (optional.isPresent()) {
                List<Concurso> lista = service.lstConcursoxIdDifDelMismo(obj.getNombre(), obj.getId());
                if (CollectionUtils.isEmpty(lista)) {
                    Concurso objSalida = service.insertandupdtConcurso(obj);
                    if (objSalida == null) {
                        salida.put("mensaje", "Error en el actualizacion ") ;
                    } else {
                        salida.put("mensaje", "Actualizacion exitosa con el ID:  " + obj.getId());
                    }
                }else {
                    salida.put("mensaje", "El concurso ya existe:  " + obj.getNombre());
                }

            }else {
                salida.put("mensaje", "No existe el ID :" + obj.getId());
            }

        } catch (Exception e) {
            e.printStackTrace();
            salida.put("mensaje", "Error en el registro " + e.getMessage());
        }
        return ResponseEntity.ok(salida);
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public ResponseEntity<HashMap<String, Object>> dltxId(@PathVariable int id){
        HashMap<String, Object> salida = new HashMap<>();

        try {
            Optional<Concurso> optional = service.buscaxId(id);
            if (optional.isPresent()) {
                service.dltxId(id);
                salida.put("mensaje", "Eliminacion exitosa");
            } else {
                salida.put("mensaje", "No existe el ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            salida.put("mensaje", "Error en el registro " + e.getMessage());
        }
        return ResponseEntity.ok(salida);
    }
}
