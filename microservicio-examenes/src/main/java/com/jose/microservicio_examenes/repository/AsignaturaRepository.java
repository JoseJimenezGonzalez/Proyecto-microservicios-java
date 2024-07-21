package com.jose.microservicio_examenes.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jose.commons_entity.models.Asignatura;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Long>{

    Optional<Asignatura> findByNombre(String nombre);
    
}
