package com.jose.microservicio_cursos.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jose.commons_entity.models.Curso;
import com.jose.microservicio_cursos.cliente.RespuestaFeignCliente;
import com.jose.microservicio_cursos.repository.CursoRepository;

@Service
public class CusoServiceImpl implements CursoService{

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    RespuestaFeignCliente respuestaFeignCliente;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findById(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    @Transactional
    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        cursoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Curso encontrarCursoPorIdAlumno(Long id) {
        return cursoRepository.encontrarCursoPorIdAlumno(id);
    }

    @Override
    public List<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId) {
        return this.respuestaFeignCliente.obtenerExamenesIdsConRespuestasAlumno(alumnoId);
    }

}
