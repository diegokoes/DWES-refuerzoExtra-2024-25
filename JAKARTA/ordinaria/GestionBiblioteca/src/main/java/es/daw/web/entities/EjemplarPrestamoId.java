package es.daw.web.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class EjemplarPrestamoId {
    private Long ejemplarId;
    private Long prestamoId;
    
    public EjemplarPrestamoId(Long ejemplarId, Long prestamoId) {
        this.ejemplarId = ejemplarId;
        this.prestamoId = prestamoId;
    }

    

}



