package com.jose.microservicio_examenes.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jose.commons_entity.models.Asignatura;
import com.jose.commons_entity.models.Examen;
import com.jose.microservicio_examenes.repository.AsignaturaRepository;
import com.jose.microservicio_examenes.repository.ExamenRepository;

@Service
public class ExamenServiceImpl implements ExamenService{

    @Autowired
    ExamenRepository examenRepository;

    @Autowired
    AsignaturaRepository asignaturaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Examen> findAll() {
        return examenRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Examen> findById(Long id) {
        return examenRepository.findById(id);
    }

    @Override
    @Transactional
    public Examen save(Examen examen) {
        return examenRepository.save(examen);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        examenRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Examen> buscarPorNombre(String nombre) {
        return examenRepository.buscarPorNombre(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Asignatura> findAllAsignaturas() {
        return asignaturaRepository.findAll();
    }

}
