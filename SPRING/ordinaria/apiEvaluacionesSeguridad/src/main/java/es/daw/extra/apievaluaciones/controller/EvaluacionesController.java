package es.daw.extra.apievaluaciones.controller;

import es.daw.extra.apievaluaciones.dto.*;
import es.daw.extra.apievaluaciones.entities.Evaluacion;
import es.daw.extra.apievaluaciones.entities.Nota;
import es.daw.extra.apievaluaciones.exceptions.EvaluacionNoEncontradaException;
import es.daw.extra.apievaluaciones.exceptions.EvaluacionSinNotaException;
import es.daw.extra.apievaluaciones.exceptions.NotaNoEncontradaException;
import es.daw.extra.apievaluaciones.repositories.EvaluacionRepository;
import es.daw.extra.apievaluaciones.repositories.NotaRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * PENDIENTE
 * Hacer un servicio de evaluaciones
 * Sería útil un servicio de transformación entity <-> dto????
 * Meter Security!!!!!! definir reglas de autenticación y autorización
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/evaluaciones")
public class EvaluacionesController {

    private final EvaluacionRepository evaluacionRepository;
    private final NotaRepository notaRepository;

    @GetMapping("/detalles")
    public ResponseEntity<List<EvaluacionDetallesDTO>> getEvaluaciones(){
        List<Evaluacion> evaluaciones = evaluacionRepository.findAll();

        List<EvaluacionDetallesDTO> resultados = evaluaciones.stream()
                .map(evaluacion -> new EvaluacionDetallesDTO(
                            evaluacion.getCodigo(),
                            evaluacion.getCurso().getNombre(),
                            evaluacion.getNotas().stream()
                                .map(nota -> new NotaDetalleDTO(
                                        nota.getNia(),
                                        nota.getCalificacion())
                                )
                                .toList())
                )
                .toList();

        return ResponseEntity.ok(resultados);

    }

    @GetMapping("/{codigo}/promedio")
    public ResponseEntity<PromedioEvaluacionDTO> obtenerPromedioEvaluacionYCurso(
            @PathVariable String codigo){

        Optional<Evaluacion> evaluacionOpt = evaluacionRepository.findByCodigo(codigo);

        if (evaluacionOpt.isEmpty()) {
            //return ResponseEntity.notFound().build(); // 404
            throw new EvaluacionNoEncontradaException("No se encontró la evluación con código "+ codigo);
        }

        Evaluacion evaluacion = evaluacionOpt.get();
        List<Nota> notas = evaluacion.getNotas();

        if (notas.isEmpty()) {
            throw new EvaluacionSinNotaException(
                    "La evaluación "+codigo+" del curso "+evaluacion.getCurso().getNombre()+" no tiene notas registradas"
            );
        }

        // ahora sí que sí calculo el promedio
        double promedio = notas.stream()
                .filter( n -> n.getCalificacion() != null)
                .mapToInt(Nota::getCalificacion)
                .average()
                .orElse(0.0);


        // Montar el DTO resultado
        PromedioEvaluacionDTO promedioDTO = new PromedioEvaluacionDTO(
                evaluacion.getCodigo(),
                evaluacion.getNombre(),
                evaluacion.getCurso().getNombre(),
                promedio
        );
        return ResponseEntity.ok(promedioDTO);

    }


    @PatchMapping("/{codigo}/notas/{nia}")
    public ResponseEntity<NotaDTO> actualizarCalificacion(
            @PathVariable String codigo,
            @PathVariable String nia,
            @RequestBody @Valid ValorNotaDTO valorNota
    ){

        // Buscar la evaluación por código
        Evaluacion ev = evaluacionRepository.findByCodigo(codigo)
                .orElseThrow(() -> new EvaluacionNoEncontradaException("Ev no encontrada: "+codigo));

        // Buscar la nota por evaluación y nía
        Nota nota = notaRepository.findByEvaluacionAndNia(ev,nia)
                .orElseThrow(() -> new NotaNoEncontradaException(nia,codigo));

        //Actualizar la calificación
        nota.setCalificacion(valorNota.getCalificacion());
        notaRepository.save(nota);

        // cosntrucción del DTO respuesta
        NotaDTO resultado = new NotaDTO(
                nota.getEvaluacion().getCodigo(),
                nota.getEvaluacion().getNombre(),
                nota.getEvaluacion().getCurso().getNombre(),
                nota.getCalificacion()
        );

        return ResponseEntity.ok(resultado);



    }


}
