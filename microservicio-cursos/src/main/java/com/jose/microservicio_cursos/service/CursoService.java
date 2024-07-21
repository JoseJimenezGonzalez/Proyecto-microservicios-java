package com.jose.microservicio_cursos.service;

import java.util.List;
import java.util.Optional;
import com.jose.commons_entity.models.Curso;

public interface CursoService {

    public List<Curso> findAll();
    public Optional<Curso> findById(Long id);
    public Curso save(Curso curso);
    public void deleteById(Long id);
    public Curso encontrarCursoPorIdAlumno(Long id);
    public List<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId);
    
}
