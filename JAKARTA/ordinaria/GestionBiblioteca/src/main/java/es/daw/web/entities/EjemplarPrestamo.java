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

    @ManyToOne
    @MapsId("ejemplarId")
    @JoinColumn(name = "ejemplar_id")
    private Ejemplar ejemplar;

    @ManyToOne
    @MapsId("prestamoId")
    @JoinColumn(name = "prestamo_id")
    private Prestamo prestamo;

    //fecha_limite_devolucion DATE NOT NULL,
    //fecha_real_devolucion DATE,
    @Column(name = "fecha_limite_devolucion", nullable = false)
    private LocalDate fechaLimiteDevolucion;

    @Column(name = "fecha_real_devolucion", nullable = false)
    private LocalDate fechaRealDevolucion;


    public EjemplarPrestamo(Prestamo prestamo, Ejemplar ejemplar) {
        this.prestamo = prestamo;
        this.ejemplar = ejemplar;

        this.id = new EjemplarPrestamoId(ejemplar.getId(),prestamo.getId());
    }


    @PrePersist
    protected void onCreate() {
        fechaLimiteDevolucion = LocalDate.now().plusDays(15); // máximo 15 días para devolver todos los libros
        // no puedo poner la fecha real porque será cuando el socio devuelva el libro
    }    

}
