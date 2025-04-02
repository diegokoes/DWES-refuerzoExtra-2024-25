package es.daw.extra.mvc.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@PropertySource("classpath:config.properties")
public class WebClientConfig {

    @Value("${api.estudiantes.url}")
    private String apiUrl; // endpoints de búsqueda http://localhost:8081/estudiantes/search

    @Value("${api.estudiantes.auth.url}")
    private String authUrl;

    @Value("${api.estudiantes}")
    private String api;

    // Para practicar con múltiples webClient
    @Bean
    @Qualifier("webClientCorto")
    public WebClient webClient(WebClient.Builder builder){
        return builder
                .baseUrl(api)
                .build();
    }

    @Bean
    public WebClient webClientAuth(WebClient.Builder builder){
        return builder
                .baseUrl(authUrl)
                .build();
    }

    @Bean
    public WebClient webClientApi(WebClient.Builder builder){
        return builder
                .baseUrl(apiUrl)
                .build();
    }

}
