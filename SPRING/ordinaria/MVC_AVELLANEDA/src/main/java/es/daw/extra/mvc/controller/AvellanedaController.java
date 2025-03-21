package es.daw.extra.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Redirigir a plantillas "a mano" sin usar MvCConfig...
@Controller
public class AvellanedaController {

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

        if (nia != null) {
            //petición API Rest find by NIA

        }else if (nombre != null && primerApellido != null && segundoApellido != null) {
            //petieción API Rest find by nombre, .....

        }


        //model.addAttribute("estudiante", ??????);
        return "estudiantes/informe";
    }

}
