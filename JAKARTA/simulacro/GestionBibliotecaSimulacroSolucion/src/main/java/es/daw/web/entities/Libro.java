package es.daw.web.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Libro {

    @Id
    private String isbn;

    @Column(nullable=false)
    private String titulo;

    private String editorial;
    

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private Set<Ejemplar> ejemplares;

    @ManyToOne
    @JoinColumn(name="autor_id")
    private Autor autor;


    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    


}
