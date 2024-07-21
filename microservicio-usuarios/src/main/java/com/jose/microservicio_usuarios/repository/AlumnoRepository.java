package com.jose.microservicio_usuarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jose.commons_entity.models.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long>{

    @Query("SELECT a FROM Alumno a WHERE a.nombre = ?1")
    List<Alumno> buscarPorNombre(String nombre);
    
}
