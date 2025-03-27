# Versión 1: proyecto OrdinariaSolucion
Sigue las instrucciones del pdf.

Tienes los recursos iniciales.

# Versión 2: Ampliación - Gestión de libros y ejemplares prestados

En Prestamo no vamos a trabajar directamente con el título del libro como un String, vas a crear una entidad Libro.

Además, vamos a tener en cuenta que existirá el libro (la obra) y ejemplares del libro (copia física concreta).

## Modelo

- Libro → contiene los datos bibliográficos: título, autor, ISBN, etc.
- Ejemplar → representa una copia física del libro.
- Prestamo → el acto de prestar uno o varios ejemplares a un socio.
- LineaPrestamo → la relación entre préstamo y ejemplar, con atributos extra.

![image](https://github.com/user-attachments/assets/42ce6d23-0a53-4799-85d0-bb6b48921ee1)

### Diagrama del modelo relacional completo

![modelo_entidad_relacion](https://github.com/user-attachments/assets/1ecab6d4-b8e9-4cc4-99e3-c848ff9cc715)

- socio
- prestamo (relacionado con socio)
- libro → ejemplar
- linea_prestamo (relacionando prestamo y ejemplar con la fecha límite incluida)


## Crea nueva base de datos Biblioteca

![image](https://github.com/user-attachments/assets/9a03bc77-0000-4624-bf49-7aee0f55f75d)



## Modifica la lógica de la aplicación para incluir la entidad libro
