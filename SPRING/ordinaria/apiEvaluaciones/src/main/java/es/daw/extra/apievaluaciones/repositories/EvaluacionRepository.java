package es.daw.extra.apievaluaciones.repositories;

import es.daw.extra.apievaluaciones.entities.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {
    Optional<Evaluacion> findByCodigo(String codigo);
}
