package es.daw.web.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String calle;
    private String ciudad;

    @Column(name="codigo_postal")
    private String codigoPostal;

    @OneToOne(mappedBy = "direccion")
    private Autor autor;

    // No hace falta
    public Direccion(){
        //autor = new Autor();
    }

    public Integer getId() {
        return id;
    }
        
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Direccion [id=" + id + ", calle=" + calle + ", ciudad=" + ciudad + ", codigoPostal=" + codigoPostal
                + "]";
    }

    
    
}
