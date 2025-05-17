package es.daw.web.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/autores-populares")
public class AutoresPopularesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> autores = Arrays.asList(
            "Gabriel García Márquez",
            "Isabel Allende",
            "George Orwell",
            "Antoine de Saint-Exupéry",
            "Julio Cortázar"
        );    
        
        request.setAttribute("autores", autores);
        request.setAttribute("titulo", "Autores mśa populares del mes");

        request.getRequestDispatcher("/autoresPopulares.jsp").forward(request, response);
        
                
    }
}
