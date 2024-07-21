package com.jose.microservicio_cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.jose.commons_entity.models.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{

    @Query("SELECT curso FROM Curso curso JOIN FETCH curso.alumnos alumno WHERE alumno.id=:id")
    public Curso encontrarCursoPorIdAlumno(Long id);

}
