package es.daw.web.cdi;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import es.daw.web.entities.Prestamo;
import es.daw.web.entities.Socio;
import es.daw.web.exceptions.JPAException;
import es.daw.web.repositories.CrudRepository;
import es.daw.web.repositories.JpaManagerCdi;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.inject.Named;

// @RequestScoped
// @Named("socioBean")
@Model
public class SocioBean {

    private Set<Socio> socios;

    private String nombre;
    private String email;
    private String telefono;
    private String libro;    

    private String mensajeError;

    // INYECCIÓN POR PROPIEDAD
    
    // @Inject
    // private CrudRepository<Socio> repoSocio;

    // public SocioBean(){
    //     socios = new LinkedHashSet<>();
    // }


    // INYECCIÓN POR CONSTRUCTOR
    private final CrudRepository<Socio> repoSocio;

    @Inject
    public SocioBean(CrudRepository<Socio> repoSocio) {
        socios = new LinkedHashSet<>();
        this.repoSocio = repoSocio;
        
    }

    public Set<Socio> getSocios() {

        try {
            socios = repoSocio.select();
            System.out.println("*********** LISTADO DE SOCIOS *************");
            for (Socio socio : socios) {
                System.out.println("* "+socio);
                if (socio.getPrestamos().isEmpty())
                    System.out.println("\t\t* [WARNING] No ha realizado préstamos.");
                else{
                    System.out.println("\t\t* Préstamos realizados:");
                    for (Prestamo prestamo : socio.getPrestamos()) {
                        System.out.println("\t\t\t* "+prestamo);
                    }
                }
            }
        } catch (JPAException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        }

        return socios;
    }


    public String crearSocio(){

        Socio socio = new Socio();
        socio.setNombre(nombre);
        socio.setEmail(email);
        socio.setTelefono(telefono);

        try {

            // comprobar si viene el título del libro para crear el préstamo
            if (libro != null && !libro.isEmpty()){
                Prestamo prestamo = new Prestamo();
                prestamo.setTituloLibro(libro);
                prestamo.setFechaDevolucion(LocalDate.now().plusDays(15));
                prestamo.setSocio(socio);

                // necesito añadir al socio el préstamo????
                // siiiiiiiii!!!! para que se guarde también el préstamo
                socio.addPrestamo(prestamo);
                //socio.setPrestamos(List.of(prestamo)); //mejor no
            }


            repoSocio.save(socio);



        } catch (JPAException e) {
            System.out.println("*********** ERROR AL CREAR EL SOCIO ***************");
            mensajeError = JpaManagerCdi.getMessageError(e);
            e.printStackTrace();
            return "error";
        }
        return "socios"; // sin flush
        //return "socios?faces-redirect=true";

    }





    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getLibro() {
        return libro;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    





}
