package es.daw.extra.apievaluaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PromedioEvaluacionDTO {
    private String codigo;
    private String nombre;
    private String curso;
    private Double promedio;
}
