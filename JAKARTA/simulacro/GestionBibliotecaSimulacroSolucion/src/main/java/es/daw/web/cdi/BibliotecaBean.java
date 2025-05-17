package es.daw.web.cdi;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
//@Model
//@SessionScoped
public class BibliotecaBean implements Serializable{

    // PROPIEDADES
    private String opcion;
    private String titulo;
    private List<String> libros;

    @Inject
    private LoginBean loginBean;

    // MÉTODO DE ACCIÓN
    public String procesarOpcion() {

        System.out.println("* procesarOpcion, opción seleccionada: "+opcion);

        // Datos de prueba porque no me estoy conectando a la base de datos
        List<String> librosDisponibles = Arrays.asList("El Quijote", "1984", "Cien años de soledad", "El señor de los anillos");
        List<String> prestamosCliente = Arrays.asList("1984 - George Orwell", "Cien años de soledad - Gabriel García Márquez");
        List<String> prestamosAdmin = Arrays.asList("1984 - Juan Pérez", "Cien años de soledad - María Gómez", "El Quijote - Carlos López");


        // Lógica para saber que listado mostrar en la vista listadoLibros.xhtml
        if ("ver-libros".equals(opcion))
        {
            System.out.println("Libros disponibles!!!");
            libros = librosDisponibles;
            titulo = "Libros disponibles";

        } else if ("consultar-prestamos".equals(opcion)){
            System.out.println("consultar prestamos!!!!!!");
            titulo = "Libros prestados a "+ loginBean.getUsername();
            libros = loginBean.isAdmin() ? prestamosAdmin : prestamosCliente;
        }

        System.out.println("*Titulo:"+titulo);
        System.out.println("Libros: "+libros);
        // resultado: plantilla JSF llamada listadoLibros
        //return "listadoLibros?faces-redirect=true"; // nueva petición y se modifica la url del navegador
        return "listadoLibros";
    }

    // GETTERS AND SETTERS
    public String getOpcion() {
        return opcion;
    }
    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }


    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getLibros() {
        return libros;
    }

    @Override
    public String toString() {
        return "BibliotecaBean [opcion=" + opcion + ", titulo=" + titulo + "]";
    }


    



    
}