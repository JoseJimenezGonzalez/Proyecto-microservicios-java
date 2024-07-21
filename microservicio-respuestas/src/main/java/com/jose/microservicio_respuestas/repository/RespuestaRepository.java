package com.jose.microservicio_respuestas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jose.commons_entity.models.Respuesta;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long>{

    @Query("SELECT respuesta FROM Respuesta respuesta JOIN FETCH respuesta.alumno alumno JOIN FETCH respuesta.pregunta pregunta JOIN FETCH pregunta.examen examen WHERE alumno.id=?1 and examen.id=?2")
    public List<Respuesta> bucarRespuestasPorAlumnoPorExamen(Long alumnoId, Long examenId);

    @Query("SELECT examen.id FROM Respuesta respuesta JOIN respuesta.alumno alumno JOIN respuesta.pregunta pregunta JOIN pregunta.examen examen WHERE alumno.id=?1 GROUP BY examen.id")
    public List<Long> encontrarExamenesIdConRespuestasPorAlumno(Long alumnoId);
    
}
