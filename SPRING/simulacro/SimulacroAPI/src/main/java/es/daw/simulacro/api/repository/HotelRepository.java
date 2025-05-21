package es.daw.simulacro.api.repository;


import es.daw.simulacro.api.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    Optional<Hotel> findByCodigo(String codigo);

    List<Hotel> findByLocalidadIgnoreCase(String localidad);

    List<Hotel> findByCategoria_Codigo(String codigoCategoria);
}

