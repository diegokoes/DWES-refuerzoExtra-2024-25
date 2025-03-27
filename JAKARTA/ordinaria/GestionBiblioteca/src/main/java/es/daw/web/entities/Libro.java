package es.daw.web.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Libro {

    @Id
    //@GeneratedValue(strategy =  GenerationType.AUTO)  // Si se usa el isbn, no se genera el valor!!!!
    private String isbn;

    @Column(nullable=false)
    private String titulo;

    private String editorial;
    private String autor;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private Set<Ejemplar> ejemplares;

    // ......


}
