package es.daw.extra.apievaluaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class NotaDTO {
    private String evaluacionCodigo;
    private String evaluacionNombre;
    private String cursoNombre;
    private Integer calificacion;
}
