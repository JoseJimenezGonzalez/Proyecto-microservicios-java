package com.jose.microservicio_usuarios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.jose.commons_entity.models.Alumno;

public interface AlumnoService {

    public List<Alumno> findAll(Pageable pageable);
    public Optional<Alumno> findById(Long id);
    public Alumno save(Alumno alumno);
    public void deleteById(Long id);
    public List<Alumno> buscarPorNombre(String nombre);
    
}
