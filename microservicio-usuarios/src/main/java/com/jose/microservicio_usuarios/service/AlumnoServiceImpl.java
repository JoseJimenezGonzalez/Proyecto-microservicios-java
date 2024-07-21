package com.jose.microservicio_usuarios.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jose.commons_entity.models.Alumno;
import com.jose.microservicio_usuarios.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService{

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> findAll(Pageable pageable) {
        return alumnoRepository.findAll(pageable).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Alumno> findById(Long id) {
        return alumnoRepository.findById(id);
    }

    @Override
    @Transactional
    public Alumno save(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        alumnoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> buscarPorNombre(String nombre) {
        return alumnoRepository.buscarPorNombre(nombre);
    }

}
