package com.jose.microservicio_usuarios.controller;

import java.io.IOException;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.jose.commons_entity.models.Alumno;
import com.jose.commons_utils.ValidationUtils;
import com.jose.microservicio_usuarios.service.AlumnoService;
import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/uploads/img/{id}")
    public ResponseEntity<?> mostrarImagen(@PathVariable (name = "id") Long id){
        Optional<Alumno> optionalAlumno = this.alumnoService.findById(id);

        if(optionalAlumno.isEmpty() || optionalAlumno.get().getFoto() == null){
            return ResponseEntity.notFound().build();
        }

        Alumno alumno = optionalAlumno.get();

        ByteArrayResource imagen = new ByteArrayResource(alumno.getFoto());

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
    }

    @GetMapping()
    public ResponseEntity<?> listar(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "2") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok().body(alumnoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable (name = "id") Long id){
        Optional<Alumno> alumnoOptional = alumnoService.findById(id);
        if(alumnoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(alumnoOptional.get());
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Alumno alumno, BindingResult resultado){

        if(resultado.hasErrors()){
            return ValidationUtils.validar(resultado);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.save(alumno));
    }

    @PostMapping("/crear-con-foto")
    public ResponseEntity<?> crearConFoto(@Valid Alumno alumno, BindingResult resultado, @RequestParam MultipartFile archivo) throws IOException{
        if(!archivo.isEmpty()){
            alumno.setFoto(archivo.getBytes());
        }
        return crear(alumno, resultado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Alumno alumno, BindingResult resultado, @PathVariable (name = "id") Long id){

        if(resultado.hasErrors()){
            return ValidationUtils.validar(resultado);
        }

        Optional<Alumno> alumnoOptional = alumnoService.findById(id);
        if(alumnoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Alumno alumnoBaseDatos = alumnoOptional.get();
        alumnoBaseDatos.setNombre(alumno.getNombre());
        alumnoBaseDatos.setApellidos(alumno.getApellidos());
        alumnoBaseDatos.setEmail(alumno.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(alumnoService.save(alumnoBaseDatos));
    }

    @PutMapping("/editar-con-foto/{id}")
    public ResponseEntity<?> editarConFoto(@Valid Alumno alumno, BindingResult resultado, @PathVariable (name = "id") Long id, @RequestParam MultipartFile archivo) throws IOException{

        if(resultado.hasErrors()){
            return ValidationUtils.validar(resultado);
        }

        Optional<Alumno> alumnoOptional = alumnoService.findById(id);
        if(alumnoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Alumno alumnoBaseDatos = alumnoOptional.get();
        alumnoBaseDatos.setNombre(alumno.getNombre());
        alumnoBaseDatos.setApellidos(alumno.getApellidos());
        alumnoBaseDatos.setEmail(alumno.getEmail());

        if(!archivo.isEmpty()){
            alumnoBaseDatos.setFoto(archivo.getBytes());
        }

        return ResponseEntity.status(HttpStatus.OK).body(alumnoService.save(alumnoBaseDatos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable(name = "id") Long id){
        alumnoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("filtrar-por-nombre/{nombre}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable (name ="nombre") String nombre){
        return ResponseEntity.ok().body(alumnoService.buscarPorNombre(nombre));
    }
}
