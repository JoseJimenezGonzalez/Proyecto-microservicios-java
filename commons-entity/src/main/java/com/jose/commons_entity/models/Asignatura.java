package com.jose.commons_entity.models;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "asignaturas")
@Data
@AllArgsConstructor
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @JsonIgnoreProperties(value = {"asignaturasHijas"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Asignatura asignaturaPadre;

    @JsonIgnoreProperties(value = {"asignaturaPadre"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "asignaturaPadre", cascade = CascadeType.ALL)
    private List<Asignatura> asignaturasHijas;

    public Asignatura() {
        this.asignaturasHijas = new ArrayList<>();
    }

}
