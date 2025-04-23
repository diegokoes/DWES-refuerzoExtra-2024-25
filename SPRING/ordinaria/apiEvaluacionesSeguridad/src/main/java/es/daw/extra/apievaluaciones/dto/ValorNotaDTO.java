package es.daw.extra.apievaluaciones.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValorNotaDTO {
    @NotNull(message="La calificación no puede estar vacía.")
    @Min(value = 1, message = "La calificación debe ser al menos 1.")
    @Max(value = 10, message = "La calificación no puedes ser mayor a 10.")
    private Integer calificacion;
}
