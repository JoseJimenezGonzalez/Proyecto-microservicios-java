package com.jose.microservicio_respuestas.service;

import java.util.List;
import com.jose.commons_entity.models.Respuesta;

public interface RespuestaService {

    public List<Respuesta> saveAll(List<Respuesta> respuestas);
    public List<Respuesta> bucarRespuestasPorAlumnoPorExamen(Long alumnoId, Long examenId);
    public List<Long> encontrarExamenesIdConRespuestasPorAlumno(Long alumnoId);
    
}
