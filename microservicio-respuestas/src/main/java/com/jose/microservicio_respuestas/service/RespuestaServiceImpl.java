package com.jose.microservicio_respuestas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jose.commons_entity.models.Respuesta;
import com.jose.microservicio_respuestas.repository.RespuestaRepository;

@Service
public class RespuestaServiceImpl implements RespuestaService{

    @Autowired
    RespuestaRepository respuestaRepository;

    @Override
    @Transactional
    public List<Respuesta> saveAll(List<Respuesta> respuestas) {
        return this.respuestaRepository.saveAll(respuestas);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Respuesta> bucarRespuestasPorAlumnoPorExamen(Long alumnoId, Long examenId) {
        return this.respuestaRepository.bucarRespuestasPorAlumnoPorExamen(alumnoId, examenId);
    }

    @Override
    public List<Long> encontrarExamenesIdConRespuestasPorAlumno(Long alumnoId) {
        return this.respuestaRepository.encontrarExamenesIdConRespuestasPorAlumno(alumnoId);
    }

}
