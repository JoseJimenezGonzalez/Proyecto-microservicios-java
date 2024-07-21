package com.jose.microservicio_examenes.service;

import java.util.Optional;
import com.jose.commons_entity.models.Asignatura;

public interface AsignaturaService {

    Optional<Asignatura> findByNombre(String nombre);
    public Asignatura save(Asignatura asignatura);

}
