package com.jose.microservicio_cursos.cliente;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservicio-respuestas")
public interface RespuestaFeignCliente {

    @GetMapping("/alumno/{alumnoId}/examenes-respondidos")
    public List<Long> obtenerExamenesIdsConRespuestasAlumno(@PathVariable Long alumnoId);
    
}
