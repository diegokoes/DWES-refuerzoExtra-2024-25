package es.daw.simulacro.api.repository;

import es.daw.simulacro.api.entity.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    Optional<Habitacion> findByCodigo(String codigo);

    List<Habitacion> findByHotel_CodigoAndOcupadaFalse(String codigoHotel);

    List<Habitacion> findByHotel_CodigoAndOcupadaFalseAndTamanoGreaterThanEqualAndPrecioNocheBetween(
            String codigoHotel, int tamanoMinimo, double precioMinimo, double precioMaximo
    );
}

