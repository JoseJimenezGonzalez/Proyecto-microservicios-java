package com.jose.microservicio_examenes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jose.commons_entity.models.Asignatura;
import com.jose.microservicio_examenes.repository.AsignaturaRepository;

@Service
public class AsignaturaServiceImpl implements AsignaturaService{

    @Autowired
    AsignaturaRepository asignaturaRepository;

    @Override
    public Optional<Asignatura> findByNombre(String nombre) {
        return this.asignaturaRepository.findByNombre(nombre);
    }

    @Override
    public Asignatura save(Asignatura asignatura) {
        return this.asignaturaRepository.save(asignatura);
    }

}
