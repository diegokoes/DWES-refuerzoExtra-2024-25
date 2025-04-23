package es.daw.extra.apievaluaciones.controller;

import es.daw.extra.apievaluaciones.dto.ExceptionDTO;
import es.daw.extra.apievaluaciones.exceptions.EvaluacionNoEncontradaException;
import es.daw.extra.apievaluaciones.exceptions.EvaluacionSinNotaException;
import es.daw.extra.apievaluaciones.exceptions.NotaNoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(EvaluacionNoEncontradaException.class)
    public ResponseEntity<ExceptionDTO> handleEvaluacionNoEncontrada(EvaluacionNoEncontradaException e) {
        ExceptionDTO error = new ExceptionDTO(
                HttpStatus.NOT_FOUND.value(),
                "Evaluación no encontrada",
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(EvaluacionSinNotaException.class)
    public ResponseEntity<ExceptionDTO> handleEvNotaNoEncontrada(EvaluacionSinNotaException e) {
        ExceptionDTO error = new ExceptionDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Evaluación sin notas",
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NotaNoEncontradaException.class)
    public ResponseEntity<ExceptionDTO> handleNotaNoEncontrada(NotaNoEncontradaException e) {
        ExceptionDTO error = new ExceptionDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Nota no encontrada",
                e.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        //String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        String message = e.getBindingResult().getFieldError().getDefaultMessage();

        ExceptionDTO error = new ExceptionDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Error de validación",
                message,
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }

}
