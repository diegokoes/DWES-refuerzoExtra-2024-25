package es.daw.extra.mvc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity // Activa la seguridad en la aplicación... // NO ES NECESARIO A PARTIR DE SPRING SECURITY 6
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/home", "/invitado","/register","/h2-console/**").permitAll()
                        .requestMatchers("/filtrar-estudiantes").hasRole("TEACHER")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login") //Usa la URL /login como página de autenticación personalizada.
                        //.defaultSuccessUrl("/productos", true) // Redirige a /productos tras iniciar sesión.
                        .defaultSuccessUrl("/principal", true) // Si el login es exitoso, va a principal
                        .failureUrl("/login?error=true") // Si el login falla, redirige con ?error=true
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Define /logout como la URL para cerrar sesión.
                        .logoutSuccessUrl("/home") // Tras cerrar sesión, el usuario vuelve a /home.
                        .permitAll()
                ) //Cross-Site Request Forgery (CSRF) es un tipo de ataque que engaña a un usuario para que realice acciones en una aplicación web sin su consentimiento o conocimiento
                .csrf(csrf -> csrf.disable()) //Desactiva protección CSRF, H2-Console lo requiere.
                .headers(headers -> headers.frameOptions(f -> f.disable()))// frames de h2-console
                .exceptionHandling(exception -> exception.accessDeniedPage("/error")); //endpoint que gestiona qué hacer si no hay permisos 403
        return http.build();
    }


    // siiiiiiiiii
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
