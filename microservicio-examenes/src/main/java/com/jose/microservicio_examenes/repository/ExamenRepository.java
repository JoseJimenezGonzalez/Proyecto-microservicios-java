package com.jose.microservicio_examenes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.jose.commons_entity.models.Examen;

public interface ExamenRepository extends JpaRepository<Examen, Long>{

    @Query("SELECT examen FROM Examen examen WHERE examen.nombre like %?1%")
    public List<Examen> buscarPorNombre(String nombre);
}
