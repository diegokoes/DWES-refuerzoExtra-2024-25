package es.daw.extra.mvc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@NoArgsConstructor
public class EstudianteDTO {
    private String nia;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String email;
    private String movil;
    private String direccion;
    private String fechaNacimiento;
    private int edad;
}
