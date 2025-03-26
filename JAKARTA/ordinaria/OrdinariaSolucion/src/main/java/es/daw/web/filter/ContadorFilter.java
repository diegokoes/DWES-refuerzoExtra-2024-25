package es.daw.web.filter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter("/index.xhtml")
public class ContadorFilter implements Filter{

    //private int contador = 0;
    private final AtomicInteger contador = new AtomicInteger(0);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        LocalDateTime ahora = LocalDateTime.now();
        //contador++;
        contador.incrementAndGet();

        System.out.println("*******************************************");
        System.out.println("Último acceso a la página principal del examen: "+ahora);
        //System.out.println("Número de accesos a la página principal del examen: "+contador);
        System.out.println("Número de accesos a la página principal del examen: "+contador);
        System.out.println("*******************************************");

        chain.doFilter(request,response);
        
    }
    

}
