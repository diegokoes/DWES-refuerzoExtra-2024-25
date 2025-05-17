package es.daw.web.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @OneToOne
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    //private Set<Libro> libros = new HashSet<>();
    private Set<Libro> libros;

    public Autor(){
        libros = new HashSet<>();
    }

    // --- getters & setters
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }


    public Set<Libro> getLibros() {
        return libros;
    }    

    public void addLibro(Libro libro){
        libros.add(libro);
        libro.setAutor(this);
    }

    public void removeLibro(Libro libro){
        libros.remove(libro);
        libro.setAutor(null);
    
    }

    @Override
    public String toString() {
        return "Autor [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + "]";
    }


    




}

