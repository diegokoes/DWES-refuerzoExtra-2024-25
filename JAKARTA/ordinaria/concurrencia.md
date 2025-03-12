# Explicación del filtro (contador)
## 🔹 1. ¿Cómo funciona un servlet filter en un servidor de aplicaciones Jakarta?
Cuando un servidor de aplicaciones (como Tomcat, WildFly o GlassFish) carga una aplicación web, inicializa los componentes declarados en web.xml o con anotaciones como @WebFilter. Estos componentes incluyen servlets y filtros.

### 📌 En el caso de UltimoAccesoIndexFilter:
El servidor de aplicaciones crea UNA única instancia de UltimoAccesoIndexFilter cuando la aplicación arranca.
Cada vez que una petición llega a index.xhtml, el servidor llama al método doFilter() de esa única instancia, pero en un hilo diferente por cada petición concurrente.
Este comportamiento se debe a que los filtros y los servlets en Jakarta EE siguen un modelo singleton por defecto, donde una única instancia es utilizada para manejar todas las solicitudes concurrentes.

## 🔹 2. ¿Qué pasa cuando llegan múltiples peticiones al mismo tiempo? ⚡
Cuando varias solicitudes llegan a index.xhtml, el servidor de aplicaciones crea múltiples hilos para manejar esas peticiones en paralelo. Todos estos hilos ejecutan el método doFilter() sobre la misma instancia de UltimoAccesoIndexFilter.

Esto significa que todos esos hilos acceden y modifican la misma variable contador al mismo tiempo.

### 📌 Ejemplo con múltiples hilos accediendo a la misma instancia
Supongamos que contador = 10, y llegan tres peticiones simultáneas. Cada una ejecuta contador ++;

Idealmente, el valor final debería ser 13, pero sin sincronización, podría pasar esto:

![image](https://github.com/user-attachments/assets/ffb082b8-90c1-4472-9a62-c4d1d280daa1)


El resultado final es 12, cuando debería ser 13! Esto se debe a una condición de carrera, porque los hilos están accediendo y modificando contador sin sincronización.

## 🔹 3. ¿Por qué contador es compartido entre hilos?

Porque es una variable de instancia de un singleton:

```
public class UltimoAccesoIndexFilter implements Filter {
    private int contador = 0; // Cada hilo accede a la misma variable
}
```
- Al ser un campo de instancia (no estático), normalmente cada objeto tendría su propio valor.
- PERO, en este caso, hay una sola instancia de UltimoAccesoIndexFilter en toda la aplicación web.
- Entonces, todos los hilos acceden a la misma variable contador en la memoria del heap.

## 🔹 4. ¿Cómo solucionar esto?
Para evitar que múltiples hilos interfieran entre sí, hay dos soluciones seguras:

**Usar AtomicInteger** (recomendada, porque evita bloqueos innecesarios):

```
private final AtomicInteger contador = new AtomicInteger(0);
```

Así, cada hilo hace un incremento atómico con contador.incrementAndGet();, evitando condiciones de carrera.

**Usar synchronized**, aunque menos eficiente:

```
synchronized (this) {
    contador++;
}
```

Esto hace que solo un hilo a la vez pueda modificar contador, pero puede generar bloqueos si hay muchas peticiones simultáneas.
