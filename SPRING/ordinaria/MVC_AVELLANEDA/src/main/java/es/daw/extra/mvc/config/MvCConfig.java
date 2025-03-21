package es.daw.extra.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvCConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/invitado").setViewName("info");
        registry.addViewController("/principal").setViewName("principal");
        registry.addViewController("/ciclos-informatica").setViewName("ciclos-info");
        registry.addViewController("/filtrar-estudiantes").setViewName("estudiantes/filtro");

    }

}
