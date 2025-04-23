package es.daw.extra.apievaluaciones.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre; // Ejemplo: "1DAW", "2DAW"

    private String descripcion; // Ejeamplo: 1º de DAW .,.,.

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evaluacion> evaluaciones;
}
