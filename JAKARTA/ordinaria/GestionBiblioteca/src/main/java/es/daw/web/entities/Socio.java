package es.daw.web.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

@Entity
//@Table(name="socios") //En el caso la tabla se llama socios!! mirar las especificiaciones!!!
//public class Socio implements Comparable<Socio>{
public class Socio {
    
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    //@Column(nullable = false) // como ya está creada la tabla, el campo no permite que sea nulo
    private String nombre;

    private String email;

    private String telefono;

    @Column(name="fecha_registro")
    private LocalDate fechaRegistro;

    @OneToMany(mappedBy = "socio", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Prestamo> prestamos;
    // PARA QUE FUERA UNIDIRECCIONAL
    // Se eliminaría el atributo List<Prestamo> prestamos en Socio
    // Desde la entidad Prestamo puedes saber a qué Socio pertenece, pero no al revés. No se tendría una lista de préstamos en Socio.

    @PrePersist
    public void PrePersist(){
        fechaRegistro = LocalDate.now();
    }

    public Socio(){
        prestamos = new ArrayList<>();
        //fechaRegistro = LocalDate.now(); // la fecha de registro es la fecha actual del sistema cuando se crea el objeto en memoria, no cuando se persiste en la base de datos
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    // public void setPrestamos(List<Prestamo> prestamos) {
    //     this.prestamos = prestamos;
    // }

    public void addPrestamo(Prestamo prestamo){
        prestamos.add(prestamo);
        // Si es bidireccional tengo que indicar que el prestamo es del socio en cuestión
        prestamo.setSocio(this); // mantener sincronía en la relación bidireccional
    }
    

    public void removePrestamo(Prestamo prestamo){
        prestamos.remove(prestamo);
        // Bidireccional
        prestamo.setSocio(null); //se queda huérfano
    }



    // --------- MÉTODOS QUE SOBREESCRIBEN LA CLASE OBJECT .........
    

    @Override
    public String toString() {
        return "Socio [id=" + id + ", nombre=" + nombre + ", email=" + email + ", telefono=" + telefono
                + ", fechaRegistro=" + fechaRegistro + "]";
    }

    // ORDEN NATURAL ...
    // @Override
    // public int compareTo(Socio arg0) {
    //     return nombre.compareTo(arg0.nombre);
    // }

}
