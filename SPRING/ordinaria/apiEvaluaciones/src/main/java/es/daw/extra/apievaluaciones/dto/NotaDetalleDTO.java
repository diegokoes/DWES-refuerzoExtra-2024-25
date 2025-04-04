package es.daw.extra.apievaluaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
//@Setter
@AllArgsConstructor
public class NotaDetalleDTO {
    private String nia;
    private Integer calificacion;
}
