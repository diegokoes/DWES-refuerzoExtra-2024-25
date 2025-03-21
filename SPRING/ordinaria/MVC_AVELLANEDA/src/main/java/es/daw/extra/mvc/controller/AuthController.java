package es.daw.extra.mvc.controller;

import es.daw.extra.mvc.entity.User;
import es.daw.extra.mvc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    // Se envía esto desde SecurityConfig
    // /login?error=true
    @GetMapping("/login")
    // vakye = "error ==> Indica que estamos buscando un parámetro llamdo error en la URL
    public String mostrarLogin(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null && error.equals("true")) {
            model.addAttribute("error", "Usuario o contraseña incorrectos. Inténtalo de nuevo");
            //model.addAttribute("error", error);
        }
        return "login";
    }

    // no tengo que gestionar el login!!! lo hace Spring Security (/login vía post)

    @GetMapping("/register")
    public String mostrarRegistro(Model model) {
        model.addAttribute("user", new User());
        return "estudiantes/registro";
    }

    @PostMapping("/register")
    public String registrar(@ModelAttribute("user") User user) {
        // registrar el usuario
        // dar de alta el usuario
        userService.registerUser(user);

        return "redirect:/login";


        // --------------------------
        // PENDIENTE!!! CAPTURAR LA EXCEPCIÓN Y GESTIONAR EL ERROR...
        // CÓMO MUESTRO EN LA VISTA EL ERROR????
        // CASO 1: nativo java
//        try{
//
//        }catch
        // CASO 2: @ControllerAdvice (String)


    }
}
