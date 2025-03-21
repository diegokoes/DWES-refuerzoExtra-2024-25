package es.daw.extra.mvc.service;

import es.daw.extra.mvc.dto.AuthRequestDTO;
import es.daw.extra.mvc.dto.AuthResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
//@RequiredArgsConstructor //Noooooo porque especifico con Qualifier que webclient inyectar
public class AuthService {

    private final WebClient webClientAuth;

    @Value("${api.estudiantes.auth.login}")
    private String login;

    @Value("${api.estudiantes.auth.password}")
    private String password;

    public AuthService(@Qualifier("webClientAuth") WebClient webClientAuth)  {
        this.webClientAuth = webClientAuth;
    }

    public String obtenerToken() {
        System.out.println("**************");
        System.out.println("login: " + login);
        System.out.println("password: " + password);
        System.out.println("**************");

        AuthRequestDTO request = new AuthRequestDTO(login,password);
        AuthResponseDTO response = webClientAuth.post()
                .header("Content-Type","application/json")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AuthResponseDTO.class)
                .block();

        return response.getToken();

    }

}
