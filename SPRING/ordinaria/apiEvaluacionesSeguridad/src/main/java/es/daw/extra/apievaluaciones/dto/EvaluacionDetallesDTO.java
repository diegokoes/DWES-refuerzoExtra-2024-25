package es.daw.extra.apievaluaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
//@Setter
@AllArgsConstructor
public class EvaluacionDetallesDTO {
    private String nombre;
    private String curso;
    private List<NotaDetalleDTO> notas;
}
