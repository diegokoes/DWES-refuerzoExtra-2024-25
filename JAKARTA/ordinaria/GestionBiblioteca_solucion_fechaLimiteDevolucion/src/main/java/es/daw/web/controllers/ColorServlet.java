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
import jakarta.servlet.http.HttpSession;

@WebServlet("/color-servlet")
public class ColorServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("******** SERVLET COLOR *******");
        // LEER DATOS DEL REQUEST!!!!
        String opcion = request.getParameter("opcion");

        System.out.println("[SERVLET] opcion: "+opcion);

        HttpSession session= request.getSession();
        session.setAttribute("colorFondo", opcion);

        response.sendRedirect("welcome.xhtml");

        


    }
}