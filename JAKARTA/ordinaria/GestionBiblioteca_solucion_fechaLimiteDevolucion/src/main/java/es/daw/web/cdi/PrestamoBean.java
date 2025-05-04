package es.daw.web.cdi;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import es.daw.web.entities.Ejemplar;
import es.daw.web.entities.EjemplarPrestamo;
import es.daw.web.entities.Prestamo;
import es.daw.web.entities.Socio;
import es.daw.web.exceptions.JPAException;
import es.daw.web.repositories.CrudRepositoryEjemplar;
import es.daw.web.repositories.CrudRepositoryEjemplarPrestamo;
import es.daw.web.repositories.CrudRepositoryPrestamo;
import es.daw.web.repositories.CrudRepositorySocio;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Model;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;

@Model 
//@ViewScoped // objetivo, poder devolver un único ejemplar y no todos los ejemplares del préstamo
//@SessionScoped
public class PrestamoBean implements Serializable{

    @Inject
    private CrudRepositorySocio socioRepository;

    @Inject
    private CrudRepositoryPrestamo prestamoRepository;

    private Set<Prestamo> prestamos;


    @Inject
    private CrudRepositoryEjemplarPrestamo ejemplarPrestamoRepository;

    private Set<EjemplarPrestamo> ejemplaresDelPrestamo;

    private Prestamo prestamoSeleccionado;


    // ----------------------------
    // PARA CREAR PRÉSTAMOS....
    private Long socioIdSeleccionado;

    private Map<Long, Boolean> seleccionados = new HashMap<>();

    @Inject
    private CrudRepositoryEjemplar ejemplarRepository;
    // ------------------------


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
    public String devolverEjemplar(EjemplarPrestamo ep){
        System.out.println("********** DEVOLVER EJEMPLAR ********");
        System.out.println(ep);
        try {

                // Cambiar la fecha real de devolución
                ep.setFechaRealDevolucion(LocalDate.now());
                ejemplarPrestamoRepository.save(ep);

                System.out.println("****** EJEMPLAR DEL PRÉSTAMO DEVUELTO ******");
                System.out.println(ep);
                System.out.println("********************************");

                verDetalles(ep.getPrestamo());

        } catch (JPAException e) {
            // PENDIENTE DECIDIR QUÉ HAGO SI HAY ERROR!!!!
            e.printStackTrace();
        }

        return null; // permanecer en la misma página


    }    

    /**
     * Crear nuevo préstamo
     * @return
     */
    public String save(){
        System.out.println("******** CREAR PRÉSTAMO *******");

        try {
            // 1. Crear el entity préstamo
            Prestamo prestamoNuevo = new Prestamo();
            System.out.println("* Socio id seleccionado: "+socioIdSeleccionado);

            // PENDIENTE: Comprobar que el socioIdSeleccionado es distinto de null y no está vacío
            // si es null o vacío redirigir a página de error  o misma página con un mensaje...

            Optional<Socio> socio = socioRepository.selectById(socioIdSeleccionado.intValue());

            System.out.println("* Socio seleccionado: "+socio);

            if (socio.isPresent()){
                prestamoNuevo.setSocio(socio.get());
                // 2. Crear el préstamo en la base de datos para obtener su id
                prestamoRepository.save(prestamoNuevo);
            }

            System.out.println("\t * Prestamo nuevo:"+prestamoNuevo);
            
            System.out.println("\n ******* LISTADO DE EJEMPLARES:");
            // Asociar los ejemplares seleccionados al préstamo
            for (Map.Entry<Long,Boolean> entry : seleccionados.entrySet()) {
                System.out.println("* key: "+entry.getKey()); // Long (identificador)
                System.out.println("* Value:"+entry.getValue()); // Boolean

                // Si es true, es que se ha seleccionado
                if (entry.getValue()){
                    Optional<Ejemplar> ejOptional = ejemplarRepository.selectById(entry.getKey().intValue());

                    if (ejOptional.isPresent()){
                        Ejemplar ejemplar = ejOptional.get();

                        // // Añadir el ejemplar al préstamo
                        // prestamoNuevo.addEjemplar(ejemplar);

                        // // Crear la entidad de la tabla intermedia PRESTAMO_EJEMPLAR
                        // // Lo creo porque tiene campos intermedios!!!
                        // EjemplarPrestamo ep = new EjemplarPrestamo(prestamoNuevo, ejemplar);

                        // Crear relación intermedia con atributos
                        EjemplarPrestamo ep = new EjemplarPrestamo(prestamoNuevo, ejemplar);

                        // Agregar la relación a ambas entidades
                        prestamoNuevo.addEjemplarPrestamo(ep);
                        ejemplar.getPrestamos().add(ep); // Asegúrate de tener este set en `Ejemplar`


                        System.out.println("\t ********** Ejemplar-Prestamo: "+ep);                        

                        // Guardar el EjemplarPrestamo en base de datos
                        //ejemplarPrestamoRepository.save(ep);

                    }

                    
                }
            }

            // Finalmente... salvar
        
            prestamoRepository.save(prestamoNuevo);
        } catch (JPAException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            // PENDIENTE!!!! GESTIONAR EL ERROR...

        }

        // se cargan todos los préstamos y debería aparecer el  nuevo creado
        return "prestamos.xhtml?faces-redirect=true";

    }



    public String prueba(){
        System.out.println("*********** PROBANDO PROBANDO .....");
        return null;
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


    public Map<Long, Boolean> getSeleccionados() {
        return seleccionados;
    }

    public void setSeleccionados(Map<Long, Boolean> seleccionados) {
        this.seleccionados = seleccionados;
    }

    public Long getSocioIdSeleccionado() {
        return socioIdSeleccionado;
    }

    public void setSocioIdSeleccionado(Long socioIdSeleccionado) {
        this.socioIdSeleccionado = socioIdSeleccionado;
    }


    

}
