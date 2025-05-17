package es.daw.web.cdi;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import es.daw.web.entities.Autor;
import es.daw.web.entities.Direccion;
import es.daw.web.entities.Libro;
import es.daw.web.exceptions.JPAException;
import es.daw.web.repositories.CrudRepository;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

@Model
public class AutorBean{

    private Set<Autor> autores = new HashSet<>();

    @Inject
    private CrudRepository<Autor> repoAutor;

    // -----------------
    private Integer autorId; // Se rellena desde el formulario
    private String nombreAutor;

    private Integer idDireccion;
    private String calle;
    private String ciudad;
    private String codigoPostal;  
    // ---------------------  

    public Set<Autor> getAutores() {
        try {
            autores = repoAutor.select();

            System.out.println("*********** LISTADO DE AUTORES *************");
            for (Autor autor : autores) {
                System.out.println("* " + autor.getNombre());
                if (autor.getLibros().isEmpty()) {
                    System.out.println("\t\t* [WARNING] No tiene libros.");
                } else {
                    for (Libro libro : autor.getLibros()) {
                        System.out.println("\t\t* Libro: " + libro.getTitulo());
                    }
                }
            }
        } catch (JPAException e) {
            e.printStackTrace();
        }

        return autores;
    }


    public String editarDireccion(int id) {

        try {
            Optional<Autor> autorOpt = repoAutor.selectById(id);

            Autor autor = repoAutor.selectById(id).orElseThrow();

            //if (autorOpt.isPresent()){
            //    Autor a = autorOpt.get();
                nombreAutor = autor.getNombre();
                idDireccion = autor.getDireccion().getId();
                calle = autor.getDireccion().getCalle();
                ciudad = autor.getDireccion().getCiudad();
                codigoPostal = autor.getDireccion().getCodigoPostal();
                autorId = autor.getId();
                //}

        } catch (JPAException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println(e.getMessage());
            // PENDIENTE!!! METER EL MESSAGE DEL ERROR!!!
            //return "errorGenerico";
        }

        return "editarDireccion";
        
    }

    public String guardarDireccion(){
        try {
            Autor autor = repoAutor.selectById(autorId).orElseThrow();

            Direccion dir = autor.getDireccion();

            dir.setCalle(calle);
            dir.setCiudad(ciudad);
            dir.setCodigoPostal(codigoPostal);

            autor.setDireccion(dir);

            repoAutor.save(autor);

            return "autores?faces-redirect=true";

            
        } catch (JPAException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            //return "error"; // pendiente!!!
            return null;
        }


    }

    // ------------

    public Integer getAutorId() {
        return autorId;
    }


    public void setAutorId(Integer autorId) {
        this.autorId = autorId;
    }


    public String getNombreAutor() {
        return nombreAutor;
    }


    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }


    public Integer getIdDireccion() {
        return idDireccion;
    }


    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }


    public String getCalle() {
        return calle;
    }


    public void setCalle(String calle) {
        this.calle = calle;
    }


    public String getCiudad() {
        return ciudad;
    }


    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }


    public String getCodigoPostal() {
        return codigoPostal;
    }


    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    // --------
    
}
