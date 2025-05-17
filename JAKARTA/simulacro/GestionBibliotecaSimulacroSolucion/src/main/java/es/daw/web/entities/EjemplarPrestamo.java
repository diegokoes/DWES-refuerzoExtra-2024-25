package es.daw.web.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="ejemplar_prestamo")
public class EjemplarPrestamo {

    @EmbeddedId
    private EjemplarPrestamoId id;

    // Este campo (ejemplar o prestamo) está relacionado con una parte de la clave primaria compuesta (EjemplarPrestamoId). 
    // Usa su ID para completar el valor del campo correspondiente en id.

    // Cuando asignas un Ejemplar a este objeto, automáticamente copia su id al campo ejemplarId dentro del EjemplarPrestamoId.
    @ManyToOne
    @MapsId("ejemplarId")
    @JoinColumn(name = "ejemplar_id")
    private Ejemplar ejemplar;

    // Cuando asignas un Prestamo a este objeto, automáticamente copia su id al campo prestamoId dentro del EjemplarPrestamoId.
    @ManyToOne
    @MapsId("prestamoId")
    @JoinColumn(name = "prestamo_id")
    private Prestamo prestamo;

    //fecha_limite_devolucion DATE NOT NULL,
    //fecha_real_devolucion DATE,
    @Column(name = "fecha_limite_devolucion", nullable = false)
    private LocalDate fechaLimiteDevolucion;

    //@Column(name = "fecha_real_devolucion", nullable = false)
    @Column(name = "fecha_real_devolucion", nullable = true)
    private LocalDate fechaRealDevolucion;

    // CONSTRUCTOR QUE RECIBE LAS DOS ENTIDADES QUE FORMA EL M2M
    public EjemplarPrestamo(Prestamo prestamo, Ejemplar ejemplar) {
        this.prestamo = prestamo;
        this.ejemplar = ejemplar;

        this.id = new EjemplarPrestamoId(ejemplar.getId(),prestamo.getId());

        this.fechaLimiteDevolucion = prestamo.getFechaPrestamo().plusDays(15);
    }

    public EjemplarPrestamo(){

    }


    @PrePersist
    protected void onCreate() {
        //fechaLimiteDevolucion = LocalDate.now().plusDays(15); // máximo 15 días para devolver todos los libros
        fechaLimiteDevolucion = prestamo.getFechaPrestamo().plusDays(15);


        System.out.println("***************** PRE PERSIST!!!!!!!!!!!!!!! ************");
        System.out.println("* fechaLimiteDevolucion: "+fechaLimiteDevolucion);
        // no puedo poner la fecha real porque será cuando el socio devuelva el libro
    }

    public EjemplarPrestamoId getId() {
        return id;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public LocalDate getFechaLimiteDevolucion() {
        return fechaLimiteDevolucion;
    }

    public LocalDate getFechaRealDevolucion() {
        return fechaRealDevolucion;
    }

    public void setFechaRealDevolucion(LocalDate fechaRealDevolucion) {
        this.fechaRealDevolucion = fechaRealDevolucion;
    }

    

    public void setFechaLimiteDevolucion(LocalDate fechaLimiteDevolucion) {
        this.fechaLimiteDevolucion = fechaLimiteDevolucion;
    }

    
    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    @Override
    public String toString() {
        return "EjemplarPrestamo [id=" + id + ", ejemplar=" + ejemplar + ", prestamo=" + prestamo
                + ", fechaLimiteDevolucion=" + fechaLimiteDevolucion + ", fechaRealDevolucion=" + fechaRealDevolucion
                + "]";
    }    

    public void prueba(){
        System.out.println("********* ALEJO!!!!!!! ********");
    }
    

}
