package es.daw.extra.apievaluaciones.repositories;

import es.daw.extra.apievaluaciones.entities.Evaluacion;
import es.daw.extra.apievaluaciones.entities.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotaRepository extends JpaRepository<Nota, Long> {

    Optional<Nota> findByEvaluacionAndNia(Evaluacion evaluacion, String nia);


    /*
        SELECT n.* FROM nota n
        INNER JOIN evaluacion e ON n.evaluacion_id = e.id
        WHERE e.codigo = ? AND n.nia = ?;
     */
}
