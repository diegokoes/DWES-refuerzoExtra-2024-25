package es.daw.web.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

@Entity
public class Prestamo {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    // --------------
    // @Column(name="titulo_libro")
    // private String tituloLibro;

    // @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    // @JoinTable(
    //         name = "ejemplar_prestamo",
    //         joinColumns = @JoinColumn(name ="ejemplar_id"),
    //         inverseJoinColumns = @JoinColumn(name = "prestamo_id")
    // )
    // private Set<Ejemplar> ejemplaresPrestados;

    @OneToMany(mappedBy = "prestamo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EjemplarPrestamo> ejemplaresPrestamo;
    // --------------


    @Column(name="fecha_prestamo")
    private LocalDate fechaPrestamo;

    // @Column(name="fecha_devolucion")
    // private LocalDate fechaDevolucion;

    // RELACIÓN BIDECCIONAL inversa....
    @ManyToOne
    @JoinColumn(name = "socio_id", nullable = false)
    private Socio socio;


    // Constructor
    public Prestamo(){
        //ejemplaresPrestados = new HashSet<>();
        ejemplaresPrestamo = new HashSet<>();

    }



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


    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    // -------- PARA AÑADIR UN EJEMPLAR AL PRÉSTAMO ----
    // public void addEjemplar(Ejemplar ejemplar){
    //     ejemplaresPrestados.add(ejemplar);
    // }

    public void addEjemplarPrestamo(EjemplarPrestamo ep) {
        ejemplaresPrestamo.add(ep);
        ep.setPrestamo(this);
    }

    public void removeEjemplarPrestamo(EjemplarPrestamo ep) {
        ejemplaresPrestamo.remove(ep);
        ep.setPrestamo(null);
    }    

    @Override
    public String toString() {
        return "Prestamo [id=" + id + ", fechaPrestamo=" + fechaPrestamo + "]";
    }

    



    
    
}
