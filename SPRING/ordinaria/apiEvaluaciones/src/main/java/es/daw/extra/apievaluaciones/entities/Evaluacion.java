package es.daw.extra.apievaluaciones.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codigo; // Ejemplo: "1ev_1", "1ev_2", "ordinaria_1", "ordinaria_2"

    @Column(nullable = false)
    private String nombre; // Ejemplo: "Primera Evaluación", "Ordinaria"

    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso; // tengo que acceder al entity curso para coger..

    @OneToMany(mappedBy = "evaluacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Nota> notas;
}
