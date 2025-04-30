package es.daw.web.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import es.daw.web.cdi.LoginBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/biblio-servlet")
public class BibliotecaServlet extends HttpServlet {

    @Inject
    LoginBean loginBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("******** SERVLET *******");
        // LEER DATOS DEL REQUEST!!!!
        String opcion = request.getParameter("opcion");

        System.out.println("[SERVLET] opcion: "+opcion);

        
        // Datos de prueba
        List<String> librosDisponibles = Arrays.asList("El Quijote", "1984", "Cien años de soledad", "El señor de los anillos");
        List<String> prestamosCliente = Arrays.asList("1984 - George Orwell", "Cien años de soledad - Gabriel García Márquez");
        List<String> prestamosAdmin = Arrays.asList("1984 - Juan Pérez", "Cien años de soledad - María Gómez", "El Quijote - Carlos López");

        if (opcion != null){
            if(opcion.equals("ver-libros")){
                request.setAttribute("titulo", "Libros disponibles");
                request.setAttribute("libros",librosDisponibles);
            }
            else if (opcion.equals("consultar-prestamos")){
                request.setAttribute("titulo", "Libros prestados a "+loginBean.getUsername());
                if (loginBean.isAdmin()){
                    //request.setAttribute("titulo", "Libros prestados a admin");
                    request.setAttribute("libros",prestamosAdmin);
                }else{
                    //request.setAttribute("titulo", "Libros prestados a cliente");
                    request.setAttribute("libros",prestamosCliente);
                }
            }
        }

        getServletContext().getRequestDispatcher("/listadoLibros.jsp").forward(request, response);


    }
}