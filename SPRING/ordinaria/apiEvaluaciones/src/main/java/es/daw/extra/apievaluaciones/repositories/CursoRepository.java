package es.daw.extra.apievaluaciones.repositories;

import es.daw.extra.apievaluaciones.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    // todos los métodos que meta aquí con Optional si procede

}
