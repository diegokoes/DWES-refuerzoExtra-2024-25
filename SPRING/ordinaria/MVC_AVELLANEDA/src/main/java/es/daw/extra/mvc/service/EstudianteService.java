package es.daw.extra.mvc.service;

import es.daw.extra.mvc.dto.EstudianteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


/**
 * PENDIENTE
 * NO SOLO CONSULTAR POR ESTUDIANTE. LLAMAR A OTROS MÉTODOS DEL API REST PARA DAR DE ALTA POR EJ ESTUDIANTE, MODIFICAR...
 * DICHOS ENPOINTS QUE REQUIERAN NO SOLO AUTENTICACIÓN, AUTORIZACIÓN...
 */
@Service
public class EstudianteService {

    private final WebClient webClientEstudiantes;

    private final AuthService authService;
    private final WebClient webClient; // el que apunta al api de estudiantes para find (api.estudiantes.url=http://localhost:8081/estudiantes/search)

    //api.estudiantes=http://localhost:8081
    private final WebClient webClientCorto; // apunta a api.estudiantes=http://localhost:8081

    @Autowired
    public EstudianteService(@Qualifier("webClientApi") WebClient webClientEstudiantes, AuthService authService, WebClient webClient, @Qualifier("webClientCorto") WebClient webClientCorto) {
        this.webClientEstudiantes = webClientEstudiantes;
        this.authService = authService;
        this.webClient = webClient;
        this.webClientCorto = webClientCorto;
    }

    /**
     * Mismo método para los dos endpooints
     * @param url la url de cada endpoint
     * @return
     */
    public EstudianteDTO filtrar(String url){
        // MEJORA!!!!  GUARDAR EL TOKEN A NIVEL DE SESIÓN.
        // NO NECESITO PEDIR NUEVO TOKEN SI SIGO LOGADO
        String token = authService.obtenerToken();

        System.out.println("****** token: " + token);

        // Pendiente.... controlar excepción al conectar con el api rest

        return webClientEstudiantes.get()
                .uri(url)
                .header("Authorization","Bearer "+token)
                .retrieve()
                .bodyToMono(EstudianteDTO.class)
                .block();

    }


    /**
     * Método específico para el endpoint findByNia
     * Aprendemos a usar uriBuilder con path y queryParam
     * @param nia
     * @return
     */
    public EstudianteDTO findByNia(String nia){
        String token = authService.obtenerToken();
        System.out.println("****** token: " + token);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/estudiantes/search/findByNia")
                        .queryParam("nia",nia)
                .build())
                .header("Authorization","Bearer "+token)
                .retrieve()
                .bodyToMono(EstudianteDTO.class)
                .block();

    }

// PENDIENTE .....
//.uri(uriBuilder -> uriBuilder
//                        .path("/estudiantes/search/findByNombreAndPrimerApellidoAndSegundoApellido")
//                        .queryParam("nombre", nombre)
//                        .queryParam("primerApellido", primerApellido)
//                        .queryParam("segundoApellido", segundoApellido)
//                        .build())


}
