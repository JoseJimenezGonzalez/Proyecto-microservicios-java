package com.jose.commons_entity.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "cursos")
@Data
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    //Un curso muchos alumnos, se pone generalmente en el lado en el que hay uno
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Alumno> alumnos;
    
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Examen> examenes;

    public Curso(){
        this.alumnos = new ArrayList<>();
        this.examenes = new ArrayList<>();
    }

    @PrePersist
    public void prePersist(){
        this.fechaCreacion = new Date();
    }

    public void agregarAlumno(Alumno alumno){
        this.alumnos.add(alumno);
    }

    public void agregarAlumnos(List<Alumno> listaAlumnos){
        this.alumnos.addAll(listaAlumnos);
    }

    public void eliminarAlumno(Alumno alumno){
        this.alumnos.remove(alumno);
    }

    public void agregarExamen(Examen examen){
        this.examenes.add(examen);
    }

    public void eliminarExamen(Examen examen){
        this.examenes.remove(examen);
    }

}