package es.daw.extra.apievaluaciones.repositories;

import es.daw.extra.apievaluaciones.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByName(String name);
}
