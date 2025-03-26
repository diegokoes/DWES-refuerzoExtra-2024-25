package es.daw.web.entities;

import java.time.LocalDate;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Prestamo {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    // PENDIENTE!!! Añadir una tabla libros con el id, ISBN, Titulo, Autor, Año publicación, Editorial...
    @Column(name="titulo_libro")
    private String tituloLibro;


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

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
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
        return "Prestamo [id=" + id + ", tituloLibro=" + tituloLibro + ", fechaPrestamo=" + fechaPrestamo
                + ", fechaDevolucion=" + fechaDevolucion + "]";
    }

    



    
    
}
