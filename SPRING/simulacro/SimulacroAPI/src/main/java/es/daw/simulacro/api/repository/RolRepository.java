package es.daw.simulacro.api.repository;

import es.daw.simulacro.api.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByName(String name);
}
