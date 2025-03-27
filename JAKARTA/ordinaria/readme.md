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

**Si tienes problema para crear la base de datos vacía desde la consola web:**
```
F:\_programs\h2\bin>java -cp h2-2.3.232.jar org.h2.tools.Shell -url jdbc:h2:~/Biblioteca -user sa

Welcome to H2 Shell 2.3.232 (2024-08-11)
Exit with Ctrl+C
Commands are case insensitive; SQL statements end with ';'
help or ?      Display this help
list           Toggle result list / stack trace mode
maxwidth       Set maximum column width (default is 100)
autocommit     Enable or disable autocommit
history        Show the last 20 statements
quit or exit   Close the connection and exit

sql> select 1;
1
1
(1 row, 59 ms)
sql> exit
Connection closed
```

## Modifica la lógica de la aplicación para incluir la entidad libro
