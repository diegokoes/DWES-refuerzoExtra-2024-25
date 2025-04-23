package es.daw.extra.apievaluaciones.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

//        Enumeration<String> headers = request.getHeaderNames();
//        while (headers.hasMoreElements()) {
//            String header = headers.nextElement();
//            System.out.println(header);
//        }

        final String authHeader = request.getHeader("Authorization");

        System.out.println("Authorization header: [" + authHeader+"]");

        // Si no hay token, la solicitud sigue su curso (para endpoint públicos)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7); //Bearer (ocupa 7)
        System.out.println("token: " + token);

        // Vamos a analizar el token
        final String username = jwtService.extractUsername(token);

        System.out.println("Username: " + username);

        // Verifica que no haya ya una autenticación previa a la solicitud
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Cargamos el usuario de la base de datos
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Verificar que el username en el token coincide con el del userDetail

            // PENDIENTE!!!

        }


        filterChain.doFilter(request, response);




    }
}
