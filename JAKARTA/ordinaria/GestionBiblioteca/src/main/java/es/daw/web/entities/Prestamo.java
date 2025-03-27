package es.daw.web.entities;

import java.time.LocalDate;
import java.util.Set;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Prestamo {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    // --------------
    // PENDIENTE!!! Añadir una tabla libros con el id, ISBN, Titulo, Autor, Año publicación, Editorial...
    // @Column(name="titulo_libro")
    // private String tituloLibro;
    // PENDIENTE CONFIGURAR ENTRE PRESTAMOS Y EJEMPLAR LA RELACIÓN MANY TO MANY
    @JoinTable(
            name = "ejemplar_prestamo",
            joinColumns = @JoinColumn(name ="ejemplar_id"),
            inverseJoinColumns = @JoinColumn(name = "prestamo_id")
    )
    private Set<Ejemplar> ejemplaresPrestados;
    // --------------


    //PENDIENTE!!! 
    //@ManyToMany
    //private Set<Libro> libros;

    @Column(name="fecha_prestamo")
    private LocalDate fechaPrestamo;

    @Column(name="fecha_devolucion")
    private LocalDate fechaDevolucion;

    // RELACIÓN BIDECCIONAL....
    @ManyToOne
    @JoinColumn(name = "socio_id", nullable = false)
    //@JoinColumn(name = "socio_kk", nullable = false) //para reproducir un error
    private Socio socio;

    @PrePersist
    public void PrePersist(){
        fechaPrestamo = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    @Override
    public String toString() {
        return "Prestamo [id=" + id + ", fechaPrestamo=" + fechaPrestamo
                + ", fechaDevolucion=" + fechaDevolucion + "]";
    }

    



    
    
}
