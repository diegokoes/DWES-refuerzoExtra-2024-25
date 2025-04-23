package es.daw.extra.apievaluaciones.exceptions;

public class NotaNoEncontradaException extends RuntimeException {
    public NotaNoEncontradaException(String nia, String codigo) {

        super("No se encontró la nota para el estudiante con " + nia + " en la evaluación " + codigo);
    }
}
