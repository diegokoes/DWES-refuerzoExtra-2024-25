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

# Filtro JwtFilter

## Funcionamiento
- Cada petición HTTP pasa por este filtro (excepto /login).
- Si la cabecera Authorization tiene un JWT, intenta validarlo.
- Si el token es válido, extrae el usuario y sus roles.
- Guarda la autenticación en SecurityContextHolder para que Spring Security la use.
 La petición continúa con la aplicación, pero ahora el usuario está autenticado.

## ¿Cuándo se ejecuta?
- Se ejecuta en cada petición HTTP entrante.
- Antes de que Spring Security determine si el usuario tiene acceso o no.
- Si no hay token, la petición sigue sin autenticación (para permitir endpoints públicos).
