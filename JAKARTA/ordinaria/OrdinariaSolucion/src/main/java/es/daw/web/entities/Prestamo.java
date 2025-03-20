package es.daw.web.entities;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Prestamo {

    
    // Pendiente completar atributos

    @ManyToOne
    @JoinColumn(name = "socio_id", nullable = false)
    //@JoinColumn(name = "socio_kk", nullable = false)
    private Socio socio;


    
    
}
