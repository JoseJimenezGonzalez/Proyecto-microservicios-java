package com.jose.microservicio_cursos.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jose.commons_entity.models.Alumno;
import com.jose.commons_entity.models.Curso;
import com.jose.commons_entity.models.Examen;
import com.jose.microservicio_cursos.service.CursoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    CursoService cursoService;

    @GetMapping
    public ResponseEntity<?> listarCursos(){
        return ResponseEntity.ok().body(cursoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCursoPorId(@PathVariable (name = "id") Long id){
        Optional<Curso> optionalCurso = cursoService.findById(id);
        if(optionalCurso.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(optionalCurso.get());
    }

    @PostMapping
    public ResponseEntity<?> crearCurso(@RequestBody Curso curso){
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(curso));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> editarCurso(@RequestBody Curso curso, @PathVariable (name = "id") Long id){
        Optional<Curso> optionalCurso = cursoService.findById(id);
        if(optionalCurso.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(cursoService.save(curso));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarCurso(Long id){
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/agregar-alumnos")
    public ResponseEntity<?> agregarAlumnosACurso(@RequestBody List<Alumno> listaAlumnos, @PathVariable (name = "id") Long id){
        Optional<Curso> optionalCurso = this.cursoService.findById(id);
        if(!optionalCurso.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Curso curso = optionalCurso.get();
        curso.agregarAlumnos(listaAlumnos);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.cursoService.save(curso));

    }

    @PutMapping("/{id}/eliminar-alumno")
    public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable (name = "id") Long id){
        Optional<Curso> optionalCurso = this.cursoService.findById(id);
        if(optionalCurso.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Curso curso = optionalCurso.get();
        curso.eliminarAlumno(alumno);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(this.cursoService.save(curso));
    }

    @PutMapping("/{id}/agregar-examenes")
    public ResponseEntity<?> agregarExamenesACurso(@RequestBody List<Examen> examenes, @PathVariable (name = "id") Long id){

        Optional<Curso> optionalCurso = this.cursoService.findById(id);

        if(optionalCurso.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Curso curso = optionalCurso.get();
        List<Examen> examenesCurso = curso.getExamenes();
        examenesCurso.addAll(examenes);

        curso.setExamenes(examenesCurso);
        return ResponseEntity.ok().body(this.cursoService.save(curso));

    }

    @PutMapping("/{id}/eliminar-examen")
    public ResponseEntity<?> eliminarExamen(@RequestBody Examen examen, @PathVariable (name = "id") Long id){
        Optional<Curso> optionalCurso = this.cursoService.findById(id);

        if(optionalCurso.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Curso curso = optionalCurso.get();
        curso.eliminarExamen(examen);
        return ResponseEntity.ok().body(this.cursoService.save(curso));
    }

    @GetMapping("/encontrar-curso-por-id-alumno/{id}")
    public ResponseEntity<?> encontrarCursoPorIdAlumno(@PathVariable (name = "id") Long id){
        Curso curso = this.cursoService.encontrarCursoPorIdAlumno(id);

        if(curso != null){
            List<Long> examenesIdRespondidos = cursoService.obtenerExamenesIdsConRespuestasAlumno(id);

            List<Examen> examenes = curso.getExamenes().stream().map(examen -> {
                if(examenesIdRespondidos.contains(examen.getId())){
                    examen.setRespondido(true);
                }
                return examen;
            }).collect(Collectors.toList());

            curso.setExamenes(examenes);
        }
        return ResponseEntity.ok(curso);
    }
    
}
