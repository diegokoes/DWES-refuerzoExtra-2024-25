# Ejercicios

## Ejercicio 1: relación OneToOne
Crea dos entidades: Persona y Pasaporte.

● Cada persona tiene exactamente un pasaporte.

● Cada pasaporte pertenece a una sola persona.

● El pasaporte se elimina automáticamente si se elimina la persona.

● Incluye atributos como nombre, apellido, nacionalidad, numeroPasaporte, fechaExpedicion, fechaVencimiento.

● La fecha de expedición se asignará automáticamente al guardar el pasaporte.

## Ejercicio 2: relación OneToMany (bidireccional)

Crea dos entidades: Usuario y Publicacion.

● Un usuario puede tener muchas publicaciones.

● Cada publicación pertenece a un solo usuario.

● Si un usuario se borra, deben eliminarse también todas sus publicaciones.

● Se debe aplicar orphanRemoval = true.

● Las publicaciones deben contener: contenido, fechaPublicacion, likes.

● Asigna automáticamente la fecha de publicación.

## Ejercicio 3: relación ManyToMany (bidireccional)

Crea las entidades Curso y Estudiante.

● Un estudiante puede estar matriculado en varios cursos.

● Un curso puede tener varios estudiantes.

● Utiliza una tabla intermedia curso_estudiante (JPA lo hace automáticamente).

● Define métodos auxiliares para añadir y eliminar estudiantes de un curso, y cursos de un estudiante.

● Incluye campos como nombreCurso, descripcion, nombreEstudiante, email.

● Se recomienda usar Set<> para evitar duplicados en la relación.

## Ejercicio 4: relación OneToOne + OneToMay

Crea dos entidades: Autor y Libro.

● Un autor puede tener muchos libros.

● Cada libro tiene un único autor.

● Si se elimina el autor, sus libros también deben eliminarse.

● Añade campos como: tituloLibro, fechaPublicacion, genero, nombreAutor.

● La relación debe ser bidireccional.

