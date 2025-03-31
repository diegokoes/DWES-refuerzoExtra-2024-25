# Versión 1

Sigue las instrucciones del pdf.

Tienes los recursos iniciales.

# Ampliaciones

## Mensaje de error: ¿he puesto mal el user o la password?

![image](https://github.com/user-attachments/assets/81d7fdf9-0923-45fe-a82d-a514b18f6f93)

___

## Al dar de alta un usuario, el rol por defecto que se especifique en un fichero de propiedades

![image](https://github.com/user-attachments/assets/118bc571-15cb-46c1-b79b-2265fdcc7d19)

___

## Manejo de excepciones propias

Por ejemplo:

![image](https://github.com/user-attachments/assets/bb02a27c-5400-45fb-8908-2e0d106e3f9e)

___

## Dar de alta usuario con lista de roles multiselección

![image](https://github.com/user-attachments/assets/7352ec51-e09c-47a0-92c9-74bd6c38e327)

Pendiente meter en base de datos más roles para las pruebas....

___

## ¿Puedo dar de alta un usuario sin asignarle un role?

¿Cómo obligar a que asignar el role sea obligatorio en User?

 ### Validación en base de datos

 Para garantizar la integridad aplicar una constraint personalizada o usar triggers en la base de datos.

 ### Validación de formularios con anotaciones (@NotEmpty) + validación en el controlador con @Valid

 Prueba a usar un DTO para el formulario así desacoplamos la lógica.

 Implementa UserDTO que contenga @NotEmpty ...

```
import jakarta.validation.constraints.*;
import java.util.Set;

public class UserRegistrationDTO {

    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    @NotEmpty(message = "Debe seleccionar al menos un rol")
    private Set<Long> roleIds;

    .....
   
}

```

```
<label>Roles:</label>
    <div th:each="rol : ${listaRoles}">
        <input type="checkbox"
               th:value="${rol.id}"
               th:field="*{roleIds}" />
        <span th:text="${rol.nombre}"></span><br/>
    </div>
```

```
public String registrarUsuario(@Valid @ModelAttribute("usuario") UserRegistrationDTO dto,
                                   BindingResult result,
                                   Model model) {

...

}
```
___

## Diferentes plantillas Thymeleaf para distintos errores

![image](https://github.com/user-attachments/assets/fef257a3-3db2-41d0-bbd8-8912de0f2645)


Por ejemplo si no responde el API Rest al que se conecta el proyecto Spring MVC:

![image](https://github.com/user-attachments/assets/18016764-7ed2-4c46-a9d2-bbb319dc8d72)


## Programación reactiva: no entra en examen

En aplicaciones reactivas (WebFlux), lo ideal es trabajar todo el flujo de forma no bloqueante, es decir, sin llamar a .block(), ya que eso rompe la reactividad y detiene el hilo.

```
public Mono<EstudianteDTO> filtrar(String url) {
    String token = authService.obtenerToken(); // Asumimos que esto también es reactivo
    return webClientEstudiantes.get()
            .uri(url)
            .header("Authorization", "Bearer " + token)
            .retrieve()
            .bodyToMono(EstudianteDTO.class);
}

```

Esto devuelve un Mono<EstudianteDTO> que todavía no ha ejecutado la petición. Solo se ejecutará cuando alguien se suscriba.

**En un controlador reactivo:**

```
@GetMapping("/filtrar")
public Mono<EstudianteDTO> filtrarEstudiante(@RequestParam String url) {
    return estudianteService.filtrar(url);
}

```

Todo fluye de manera asincrónica. Ideal para aplicaciones con muchas peticiones concurrentes.

**Prueba a obtener el token de forma reactiva.**



