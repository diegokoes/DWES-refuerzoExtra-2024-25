package es.daw.extra.mvc.controller;

import es.daw.extra.mvc.dto.EstudianteDTO;
import es.daw.extra.mvc.service.EstudianteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Redirigir a plantillas "a mano" sin usar MvCConfig...
@Controller
public class AvellanedaController {
    private final EstudianteService estudianteService;

    public AvellanedaController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

//    @GetMapping("/invitado")
//    public String invitado() {
//        return "info";
//    }

//    @GetMapping("/ciclos-informatica")
//    public String ciclosInformatica() {
//        return "ciclos-info";
//    }

    @GetMapping("/filtrar")
    public String filtrar(
         @RequestParam(required = false) String nia,
         @RequestParam(required = false) String nombre,
         @RequestParam(required = false) String primerApellido,
         @RequestParam(required = false) String segundoApellido,
         Model model
    ) {

        EstudianteDTO estudianteDTO = null;

        if (nia != null) {
            //petición API Rest find by NIA
            // OPCIÓN 1: con el método del servicio que vale para los dos endpoints
            //estudianteDTO = estudianteService.filtrar("/findByNia?nia=" + nia);

            // OPCIÓN 2: con un método específico
            estudianteDTO = estudianteService.findByNia(nia);



        }else if (nombre != null && primerApellido != null && segundoApellido != null) {
            //petieción API Rest find by nombre, .....
            String url = String.format("/findByNombreAndPrimerApellidoAndSegundoApellido?nombre=%s&primerApellido=%s&segundoApellido=%s", nombre, primerApellido, segundoApellido);
            //estudianteDTO = estudianteService.filtrar("/findByNombreAndPrimerApellidoAndSegundoApellido?nombre=" + nombre
            //                    + "&primerApellido="+ primerApellido + "&segundoApellido="+segundoApellido);
            estudianteDTO = estudianteService.filtrar(url);


        }
        System.out.println(estudianteDTO);

        model.addAttribute("estudiante", estudianteDTO);
        return "estudiantes/informe";
    }

}
