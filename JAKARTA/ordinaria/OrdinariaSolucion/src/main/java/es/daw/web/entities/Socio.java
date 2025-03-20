package es.daw.web.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
//@Table(name="socios") //En el caso la tabla se llama socios!! mirar las especificiaciones!!!
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

    @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prestamo> prestamos;




}
