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

    // JPA necesita un constructor vacío
    public EjemplarPrestamoId(){
        
    }

    @Override
    public String toString() {
        return "EjemplarPrestamoId [ejemplarId=" + ejemplarId + ", prestamoId=" + prestamoId + "]";
    }
    

}



