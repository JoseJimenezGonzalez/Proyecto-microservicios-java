package com.jose.microservicio_respuestas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import com.jose.commons_entity.models.Respuesta;
import com.jose.microservicio_respuestas.service.RespuestaService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody List<Respuesta> respuestas){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.respuestaService.saveAll(respuestas));
    }

    @GetMapping("/alumno/{alumnoId}/examen/{examenId}")
    public ResponseEntity<?> obtenerRespuestasPorAlumnoPorExamen(@PathVariable Long alumnoId, @PathVariable Long examenId){

        List<Respuesta> respuestas = this.respuestaService.bucarRespuestasPorAlumnoPorExamen(alumnoId, examenId);
        return ResponseEntity.ok(respuestas);

    }

    @GetMapping("/alumno/{alumnoId}/examenes-respondidos")
    public ResponseEntity<?> obtenerExamenesIdsConRespuestasAlumno(@PathVariable Long alumnoId){
        List<Long> listaExamenesId = this.respuestaService.encontrarExamenesIdConRespuestasPorAlumno(alumnoId);
        return ResponseEntity.ok(listaExamenesId);
    }


}
