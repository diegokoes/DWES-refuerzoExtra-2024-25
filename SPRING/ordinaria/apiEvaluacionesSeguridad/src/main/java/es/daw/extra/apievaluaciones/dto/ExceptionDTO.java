package es.daw.extra.apievaluaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExceptionDTO {
    // Pendiente averiguar anotación para indicar a Jackson que la propiedad en el json tiene otro nombre
    // Pendiente recopilar más anotaciones para Jackson más útiles
    private int status;
    private String error;
    private String mensaje;
    private LocalDateTime timestamp;
}
