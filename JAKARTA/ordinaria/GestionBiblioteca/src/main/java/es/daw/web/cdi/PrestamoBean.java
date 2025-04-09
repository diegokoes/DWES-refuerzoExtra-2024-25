package es.daw.web.cdi;

import java.time.LocalDate;
import java.util.Set;

import es.daw.web.entities.EjemplarPrestamo;
import es.daw.web.entities.Prestamo;
import es.daw.web.exceptions.JPAException;
import es.daw.web.repositories.CrudRepositoryEjemplarPrestamo;
import es.daw.web.repositories.CrudRepositoryPrestamo;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

@Model
public class PrestamoBean {

    @Inject
    private CrudRepositoryPrestamo prestamoRepository;

    private Set<Prestamo> prestamos;


    @Inject
    private CrudRepositoryEjemplarPrestamo ejemplarPrestamoRepository;

    private Set<EjemplarPrestamo> ejemplaresDelPrestamo;

    private Prestamo prestamoSeleccionado;

    // ------- MÉTODOS ---------

    public Set<Prestamo> getPrestamos() {

        // Consulta a BD
        try {
            prestamos = prestamoRepository.select();
        } catch (JPAException e) {
            // TODO Auto-generated catch block
            // PENDIENTE DECIDIR QUÉ HACEMOS ANTE UN ERROR JPA!!!!
            e.printStackTrace();
        }

        return prestamos;
    }

    /**
     * Devolver los ejemplares de un préstamo concreto
     * @param p
     * @return
     */
    public String verDetalles(Prestamo p){
            prestamoSeleccionado = p;
            try {
                ejemplaresDelPrestamo = ejemplarPrestamoRepository.findEjemplaresByPrestamoId(p.getId());

            } catch (JPAException e) {
                // PENDIENTE DECIDIR QUÉ HAGO SI HAY ERROR!!!!
                e.printStackTrace();
                ejemplaresDelPrestamo = Set.of(); // lista vacía si hay error... no se han podido cargar los ejemplares
            }

            return null; // permanecer en la misma página

    }


    /**
     * Devolver todos los libros de un préstamo (a la vez!!!!)
     * PENDIENTE!!!! AL DEVOLVER TODOS LOS LIBROS QUE SE BORRE EL PRÉSTAMO ENTERO DE LA BD
     * @param p Objeto préstamo a devolver
     * @return nombre de la vista
     */
    public String devolver(Prestamo p){
        prestamoSeleccionado = p;
        // Poner a todos los ejemplares del préstamo la fecha real de devolución (la del sistema)
        try {

            ejemplaresDelPrestamo = ejemplarPrestamoRepository.findEjemplaresByPrestamoId(p.getId());

            for (EjemplarPrestamo ep : ejemplaresDelPrestamo) {
                // Cambiar la fecha real de devolución
                ep.setFechaRealDevolucion(LocalDate.now());
                ejemplarPrestamoRepository.save(ep);

            }



        } catch (JPAException e) {
            // PENDIENTE DECIDIR QUÉ HAGO SI HAY ERROR!!!!
            e.printStackTrace();
            ejemplaresDelPrestamo = Set.of(); // lista vacía si hay error... no se han podido cargar los ejemplares
        }

        return null; // permanecer en la misma página


    }

    /**
     * 
     * @param ep
     * @return
     */
    public String devolverEjemplar(){
        System.out.println("********** DEVOLVER EJEMPLAR ********");
        // System.out.println(ep);
        // try {

        //         // Cambiar la fecha real de devolución
        //         ep.setFechaRealDevolucion(LocalDate.now());
        //         ejemplarPrestamoRepository.save(ep);

        //         System.out.println("****** EJEMPLAR DEL PRÉSTAMO DEVUELTO ******");
        //         System.out.println(ep);
        //         System.out.println("********************************");

        //         verDetalles(ep.getPrestamo());

        // } catch (JPAException e) {
        //     // PENDIENTE DECIDIR QUÉ HAGO SI HAY ERROR!!!!
        //     e.printStackTrace();
        // }

        return null; // permanecer en la misma página


    }    

    /**
     * 
     * @return
     */
    public Set<EjemplarPrestamo> getEjemplaresDelPrestamo() {
        return ejemplaresDelPrestamo;
    }

    public Prestamo getPrestamoSeleccionado() {
        return prestamoSeleccionado;
    }


    

}
