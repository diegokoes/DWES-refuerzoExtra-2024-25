# VERSIÓN ORIGINAL

Sigue las instrucciones del pdf.

Tienes los recursos iniciales.

___

# AMPLIACIONES: Mejoras y nuevas funcionalidades a añadir al proyecto

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

___

## Gestionar excepción si hay problemas al conectar con el Api Rest (WebClientRequestException)

```
    @Autowired
    public AuthService(@Qualifier("webClientAuth") WebClient webClientAuth2)  {
        this.webClientAuth = webClientAuth2;
    }

    // PENDIENTE GESTIONAR EXCEPCIÓN SI HAY PROBLEMAS AL CONECTAR AL API REST (WebClientRequestException)
    // Crear excepción propia ConnectApiRestException... (creo y propago)
    // Implementar un @ControllerAdvice (@ExceptionHandler de dicha excepción)
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

```

___

## EstudianteService

### Llamar a otros endpoints del Api rest

No solo consultar por estudiantes.

Llamar a otros endpoints pOST, PUT... (dar de alta estudiante, modificar...)

Que dichos endpoints requieran no solo autenticación, sino autorización basada en roles.

### Implementar findByNombreCompleto con uriBuilder

```
.uri(uriBuilder -> uriBuilder
                        .path("/estudiantes/search/findByNombreAndPrimerApellidoAndSegundoApellido")
                        .queryParam("nombre", nombre)
                        .queryParam("primerApellido", primerApellido)
                        .queryParam("segundoApellido", segundoApellido)
                        .build())
```

___

## Plantillas para gestión de filtros

Diseñar una vista Thymeleaf para realizar diferentes filtros. 

Por ejemplo un formulario donde filtra estudiantes por nia, nombre, apellido...

**Pasar de esto:**

![image](https://github.com/user-attachments/assets/5827cfaa-04eb-4ae7-8db5-08ec74f9b87f)


**A esto:**

![image](https://github.com/user-attachments/assets/41ee93f1-9f19-4a5d-ae90-3e93dda411e4)

___

## MEJORAS DEL API evaluaciones

```
/**
 * PENDIENTE
 * Hacer un servicio de evaluaciones
 * Sería útil un servicio de transformación entity <-> dto????
 * Meter Security!!!!!! definir reglas de autenticación y autorización
 */
```

## Reglas de autenticación y autorización

Endpoints:
 1. consultar cursos -> público
 2. evaluaciones detalle -> requiere autenticación
 3. promedio evaluaciones -> requiere ROLE_PROFESOR
 4. patch (modificar calificación de un estudiante en una evaluación concreta) -> requiere ROLE_ADMIN

Pasos:
- Necesitamos implementar el paquete Security.
- Añadir los Entities...
- Añadir en la BD las tablas Roles y usuarios.... no!!! los entities si están bien etiquetados servirán para que Spring cree las tablas correctamente.
- Añadir en import.sql la creación de roles y usuarios de prueba.
  
Configurar SecurityConfig con las reglas de seguridad.
___

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



