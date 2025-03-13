# Sesiones vs Cookies

Las sesiones y las cookies son herramientas clave para mantener el estado y mejorar la experiencia del usuario. 

![image](https://github.com/user-attachments/assets/b4c3ecc2-ca94-4031-acac-82277266d803)


## Sesiones

Una sesión en una aplicación web se utiliza para almacenar datos relacionados con el estado del usuario mientras interactúa con la aplicación. 

Este almacenamiento es temporal y se mantiene durante el tiempo que dure la sesión del usuario (generalmente hasta que cierre el navegador o termine la sesión explícitamente). 

En Jakarta, se suele manejar mediante un objeto HttpSession.

### Ejemplos de lo que se guarda en una sesión:

- **Datos del usuario:** Información crítica sobre el usuario que no se debe compartir con otros (como nombre, identificador de usuario, roles de acceso, preferencias, etc.). Ejemplo: session.setAttribute("usuario", usuario);
- **Datos temporales de autenticación:** Información de autenticación como el ID de usuario o el estado de si el usuario ha iniciado sesión correctamente.
- **Carrito de compras:** Si la aplicación es un e-commerce, el carrito de compras puede guardarse en la sesión del usuario mientras navega entre páginas.
- **Preferencias de la aplicación:** Configuraciones personalizadas del usuario, como su idioma preferido, tema visual, entre otros.
- **Tokens de seguridad:** En algunos casos, se utiliza la sesión para almacenar tokens que validan solicitudes o protegen contra ataques CSRF (Cross-Site Request Forgery).

### Ventajas de usar sesiones:

- Los datos son accesibles a través del servidor sin necesidad de almacenarlos en el cliente (lo que hace que sea más seguro).
- Son ideales para almacenar información sensible y para gestionar la autenticación del usuario a lo largo de la aplicación.

## Cookies

Las cookies son pequeños archivos almacenados en el navegador del usuario que permiten guardar información persistente entre las sesiones. 

A diferencia de las sesiones, las cookies se mantienen incluso después de que el usuario cierre el navegador o apague su computadora, a menos que se configure una fecha de expiración específica.

Ejemplos de lo que se guarda en las cookies:

- **Preferencias del usuario:** Información que puede personalizar la experiencia del usuario, como el idioma preferido, el tema de la interfaz, o configuraciones similares. Ejemplo: document.cookie = "language=es; path=/; expires=Thu, 01 Jan 2026 12:00:00 UTC";
. **Tokens de autenticación:** En algunos casos, especialmente en aplicaciones con autenticación basada en tokens (como JWT), el token de sesión puede almacenarse en una cookie. Este enfoque se usa para que el token persista entre sesiones.
- **Datos de seguimiento y analítica:** Cookies que permiten a la aplicación o a servicios de terceros (como Google Analytics) realizar un seguimiento del comportamiento del usuario, como qué páginas visitó o por cuánto tiempo estuvo en ellas.
- **Preferencias no sensibles:** Datos que no son críticos para la seguridad, como configuraciones de visualización (por ejemplo, si el usuario prefiere una vista en modo oscuro).
- **Identificadores únicos:** En ocasiones, las aplicaciones guardan un identificador único de sesión o de usuario que ayuda a reconocer al usuario en futuras visitas sin necesidad de una autenticación completa cada vez.

### Ventajas de usar cookies:

- Son útiles para almacenar información que se debe recordar entre sesiones (por ejemplo, preferencias de usuario o autenticación).
- Se almacenan en el cliente, lo que reduce la carga en el servidor y permite a la aplicación ser más escalable

## Diferencias clave entre sesiones y cookies
- **Persistencia:** Las sesiones son temporales y se mantienen durante la duración de la sesión del navegador, mientras que las cookies pueden configurarse para ser persistentes incluso después de cerrar el navegador.
- **Seguridad:** La sesión suele ser más segura ya que los datos se almacenan en el servidor, mientras que las cookies pueden ser accesibles y manipulables desde el cliente.
- **Capacidad de almacenamiento:** Las cookies tienen un tamaño limitado (generalmente alrededor de 4 KB por dominio), mientras que las sesiones pueden manejar más datos, ya que se almacenan en el servidor.

 ## RELATIVO A GUARDAR LA PREFERENCIA DE COLOR EN EL EJERCICIO

 Yo os he pedido usar la sesión para evaluar que sabéis usar sesiones en aplicaciones web Jakarta (ya sea con HttpSession o un CDI Bean @SessionScope), pero tened en cuenta:

 - La preferencia del color de fondo de la aplicación es una configuración personalizada que no es sensible ni crítica en términos de seguridad. 
 - Es una preferencia que probablemente el usuario querrá que se recuerde entre sesiones, incluso si cierra el navegador y lo vuelve a abrir en el futuro.

Por lo tanto, la mejor opción sería guardarla en una cookie. 

 ![image](https://github.com/user-attachments/assets/e316c36a-bf92-41bb-8b0f-d33dd5184faf)
