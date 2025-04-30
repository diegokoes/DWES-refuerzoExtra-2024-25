package es.daw.web.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Ejemplar {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    
    private String codigo_inventario;


    @ManyToOne
    @JoinColumn(name = "libro_isbn", nullable = false)
    Libro libro;

    @ManyToMany(mappedBy = "ejemplaresPrestados")
    private Set<Prestamo> prestamos;

    public Long getId() {
        return id;
    }

    public String getCodigo_inventario() {
        return codigo_inventario;
    }

    public Libro getLibro() {
        return libro;
    }

    @Override
    public String toString() {
        return "Ejemplar [id=" + id + ", codigo_inventario=" + codigo_inventario + ", libro=" + libro + "]";
    }

    
    
}
