package com.jose.microservicio_examenes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jose.commons_entity.models.Asignatura;
import com.jose.commons_entity.models.Examen;
import com.jose.commons_entity.models.Pregunta;
import com.jose.microservicio_examenes.service.AsignaturaService;
import com.jose.microservicio_examenes.service.ExamenService;
import jakarta.validation.Valid;
import com.jose.commons_utils.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/examenes")
public class ExamenController {
    
    @Autowired
    ExamenService examenService;

    @Autowired
    AsignaturaService asignaturaService;

    @GetMapping
    public ResponseEntity<?> listarExamenes(){
        return ResponseEntity.ok().body(examenService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarExamenPorId(@PathVariable (name = "id") Long id){
        Optional<Examen> optionalExamen = examenService.findById(id);
        if(optionalExamen.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().body(optionalExamen.get());
    }

    @DeleteMapping
    public ResponseEntity<?> eliminarExamen(Long id){
        examenService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<?> crearExamen(@Valid @RequestBody Examen examen, BindingResult resultado){

        String nombreAsignatura = examen.getAsignatura().getNombre();
        Optional<Asignatura> optionalAsignatura = this.asignaturaService.findByNombre(nombreAsignatura);

        //Para hibernate no es una referencia valida, por eso se le asigna a examen una instancia de asignatura que esta gestionada por hibernate
        if(optionalAsignatura.isPresent()){
            examen.setAsignatura(optionalAsignatura.get());
        }else{
            this.asignaturaService.save(examen.getAsignatura());
        }

        if(resultado.hasErrors()){
            return ValidationUtils.validar(resultado);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(examenService.save(examen));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Examen examen, @PathVariable Long id){
        Optional<Examen> optionalExamen = examenService.findById(id);
        if(!optionalExamen.isPresent()){
            return ResponseEntity.notFound().build(); 
        }
        Examen examenBaseDatos = optionalExamen.get();
        examenBaseDatos.setNombre(examen.getNombre());

        List<Pregunta> preguntasEliminadas = new ArrayList<>();

        examenBaseDatos.getPreguntas().forEach(preguntaExamenBaseDatos -> {
            if(!examen.getPreguntas().contains(preguntaExamenBaseDatos)){
                preguntasEliminadas.add(preguntaExamenBaseDatos);
            }
        });

        preguntasEliminadas.forEach(pregunta -> {
            examenBaseDatos.eliminarPregunta(pregunta);
        });

        examenBaseDatos.setPreguntas(examen.getPreguntas());

        return ResponseEntity.status(HttpStatus.CREATED).body(examenService.save(examenBaseDatos));
    }

    @PutMapping("/{id}/agregar-pregunta")
    public ResponseEntity<?> agregarPreguntaAExamen(@RequestBody Pregunta pregunta, @PathVariable Long id){

        Optional<Examen> optionalExamen = examenService.findById(id);

        if(!optionalExamen.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Examen examenBaseDatos = optionalExamen.get();

        List<Pregunta> preguntasExamen = new ArrayList<>(examenBaseDatos.getPreguntas());
        preguntasExamen.add(pregunta);

        examenBaseDatos.setPreguntas(preguntasExamen);

        return ResponseEntity.ok().body(examenService.save(examenBaseDatos));
    }

    @GetMapping("/buscar-por-nombre/{nombre}")
    public ResponseEntity<?> buscarExamenPorNombre(@PathVariable (name = "nombre") String nombre){
        return ResponseEntity.ok().body(this.examenService.buscarPorNombre(nombre));
    }

    @GetMapping("/asignaturas")
    public ResponseEntity<?> listarAsignaturas(){
        return ResponseEntity.ok().body(this.examenService.findAllAsignaturas());
    }

}
