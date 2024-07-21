package com.jose.microservicio_examenes.service;

import java.util.List;
import java.util.Optional;

import com.jose.commons_entity.models.Asignatura;
import com.jose.commons_entity.models.Examen;

public interface ExamenService {

    public List<Examen> findAll();
    public Optional<Examen> findById(Long id);
    public Examen save(Examen examen);
    public void deleteById(Long id);
    public List<Examen> buscarPorNombre(String nombre);

    public List<Asignatura> findAllAsignaturas();

}
