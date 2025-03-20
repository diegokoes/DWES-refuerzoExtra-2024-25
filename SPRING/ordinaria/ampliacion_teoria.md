# Spring Security: roles

En Spring Security, el prefijo "ROLE_" se usa como una convención para identificar roles dentro del sistema de autenticación y autorización.

Sin embargo, no es obligatorio almacenarlo en la base de datos con este prefijo.

Spring Security lo usa internamente para diferenciar roles de otras autorizaciones.

Spring Security distingue entre:
- Roles (Ejemplo: ROLE_USER, ROLE_ADMIN).
- Autorizaciones generales (Ejemplo: READ_PRIVILEGE, WRITE_PRIVILEGE).
  
Cuando utilizas métodos como hasRole("USER"), internamente Spring Security busca "ROLE_USER".

Si deseas evitar el prefijo, podrías usar hasAuthority("USER"), ya que hasAuthority() evalúa la cadena tal como está en la base de datos.

- Si la BD guarda ROLE_ADMIN, usa hasRole("ADMIN").
- Si la BD guarda ADMIN, usa hasAuthority("ADMIN").
- Puedes transformar los datos en CustomUserDetailsService si quieres cambiar el formato.

## 🔍 Diferencia entre ROLE_ y Authority en Spring Security
En Spring Security, cuando trabajamos con autorización, existen dos conceptos importantes:

- Roles (ROLE_) → Representan niveles de acceso (Ej: ROLE_ADMIN, ROLE_USER).
- Authorities (Permisos específicos) → Pueden ser permisos más finos dentro de los roles (Ej: READ_PRIVILEGE, WRITE_PRIVILEGE).
- Spring Security espera que los roles comiencen con ROLE_, mientras que las authorities pueden ser más flexibles y representar permisos granulares.

**Si tu aplicación solo maneja roles generales (ADMIN, USER):**

🔹 Usar ROLE_ es más sencillo porque Spring Security ya lo reconoce de forma nativa.

**Si necesitas permisos más específicos dentro de los roles (READ, WRITE, DELETE):**

🔹 Usar Authority es mejor, porque permite mayor control de permisos.

Si decides usar Authorities, los usuarios pueden tener permisos individuales sin depender de un rol predefinido.

___

# Spring Security para una aplicación MVC

1. Debemos proteger las rutas a las páginas HTML (.html en src/main/resources/templates).
2. La autenticación se maneja con formularios de login en lugar de JWT o tokens.
3. Debemos manejar sesiones de usuario en lugar de autenticación stateless.
4. Spring Security automáticamente redirige a una página de login si un usuario no está autenticado.

___

# JWT

## Filtro JwtFilter

- Cada petición HTTP pasa por este filtro (excepto /login).
- Si la cabecera Authorization tiene un JWT, intenta validarlo.
- Si el token es válido, extrae el usuario y sus roles.
- Guarda la autenticación en SecurityContextHolder para que Spring Security la use.
  
 La petición continúa con la aplicación, pero ahora el usuario está autenticado.

- Se ejecuta en cada petición HTTP entrante.
- Antes de que Spring Security determine si el usuario tiene acceso o no.
- Si no hay token, la petición sigue sin autenticación (para permitir endpoints públicos).

___

# Spring Data JPA

Spring Data JPA es un proyecto dentro del ecosistema de Spring que proporciona una implementación de la API JPA (Java Persistence API) estándar para la persistencia de datos en bases de datos relacionales y se superpone a Hibernate.

JPA es una especificación estándar de Java para el mapeo objeto-relacional (ORM) y proporciona una forma fácil y consistente de interactuar con una base de datos relacional utilizando objetos Java. 

Spring Data JPA proporciona una capa de abstracción adicional en la parte superior de la API JPA, lo que hace que sea aún más fácil trabajar con bases de datos relacionales que usando Hibernate solamente.


