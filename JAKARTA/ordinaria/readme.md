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

- **socio:** relacionado OneToMany con prestamo
- **prestamo:** relacionado ManyToOne con socio
- **libro:** relacionado OneToMany con ejemplar
- **ejemplar:** relacionado ManyToOne con libro
- **linea_prestamo (tabla ejemplar_prestamo):** relacionando prestamo y ejemplar (ManyToMany) con la fecha límite incluida


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

**Ejecuta biblioteca.sql** para que se creen las tablas y campos de prueba.

**No olvides añadir la password al usuario sa!!!!**

```
ALTER USER sa SET PASSWORD 'sa';
```

**Configura el DataSource en standalone.xml de Wildfly**

```
                <datasource jndi-name="java:/BibliotecaDS" pool-name="BibliotecaDS">
                    <connection-url>jdbc:h2:file:~/biblioteca;AUTO_SERVER=TRUE</connection-url>
                    <driver-class>org.h2.Driver</driver-class>
                    <driver>h2</driver>
                    <security user-name="sa" password="sa"/>
                </datasource>

```

**Configura adecuadamente al archivo persistence.xml**

```
    <persistence-unit name="BibliotecaDS" transaction-type="JTA">
        <jta-data-source>java:/BibliotecaDS</jta-data-source>

        <class>es.daw.web.entities.User</class>
        <class>es.daw.web.entities.Rol</class>
        <class>es.daw.web.entities.Socio</class>
        <class>es.daw.web.entities.Prestamo</class>
        <class>es.daw.web.entities.Libro</class>
        <class>es.daw.web.entities.Ejemplar</class>
        <class>es.daw.web.entities.EjemplarPrestamo</class>
        
        <properties>
            <property name="jakarta.persistence.schema-generation.database.action" value="validate"/>
        </properties>
    </persistence-unit>
```

## Estructura de vistas

![image](https://github.com/user-attachments/assets/69a962c7-4a76-4589-9234-8ba44bf49653)

___


### prestamos.xhtml (vista principal)
- Lista de préstamos.
- Botón “Nuevo préstamo”.
- Botón “Devolver préstamo”.

La parte de diseño corre de tu cuenta ;-)

![image](https://github.com/user-attachments/assets/5938b6dc-7fb9-4767-a725-f95599874308)


___

### VER DETALLE DEL PRÉSTAMO

Al ver el detalle de un préstamo, aparecen todos los ejemplares asociados:


![image](https://github.com/user-attachments/assets/ec2c1190-dde1-4268-93a4-5b6609cb8e01)

___

### DEVOLVER TODOS LOS EJEMPLARES DE UN PRÉSTAMO

En el listado, tenemos la opción de "Devolver todos los libros del Préstamo", de forma que por cada ejemplar prestado, se asigna la fecha real de devolución.

Por ejemplo, Luis Pérez, ha devuelto todos los ejemplares y tienen su fecha real de devolución asignada (no vacía):

![image](https://github.com/user-attachments/assets/6fd58fa5-1e09-441b-8f05-31160827fb68)


___

### DEVOLVER EJEMPLARES SUELTOS DEL PRÉSTAMO, NO TODOS LOS EJEMPLARES DEL PRÉSTAMO 

Está pendiente, que al ver los detalles de un préstamo, puedas seleccionar un único ejemplar y devolver ese ejemplar:

![image](https://github.com/user-attachments/assets/78c6c1a5-83df-40fc-9de4-5135e5de8fe7)


Hemos implementado el código en **PrestamoBean** pero no se ejecuta el método devolverEjemplar por problemas con JSF... **NO LE DEDICAMOS MÁS TIEMPO!!!***:

```
            <h:column>
                    <h:commandButton value="Devolver ejemplar" action="#{prestamoBean.devolverEjemplar(ejemplarPrestamo)}" />
            </h:column>   
```

___

### NUEVO PRÉSTAMO (PENDIENTE COMPLETAR LA SEMANA 28 DE MAYO)

- Selección de socio.
- Lista de ejemplares disponibles (checkbox).
- Botón “Guardar”.

![image](https://github.com/user-attachments/assets/c138e3ff-f3b8-40a3-be7b-85fc89515164)

### Resolver problema fecha "FECHA_LIMITE_DEVOLUCION" no permite valores nulos

El miércoles 30 de abril obteníamos el siguiente error al crear el préstamo, a pesar de que la FECHA_LIMITE_DEVOLUCION no era null:

```
ERROR [org.hibernate.engine.jdbc.spi.SqlExceptionHelper] (default task-1) La columna "FECHA_LIMITE_DEVOLUCION" no permite valores nulos (NULL)
NULL not allowed for column "FECHA_LIMITE_DEVOLUCION"; SQL statement:
insert into ejemplar_prestamo (ejemplar_id,prestamo_id) values (?,?)
```

Tras muchas pruebas y repasar JPA, el problema viene por la forma en que etiquetamos relaciones **manytomany** cuando la tabla intermedia tiene campos adicionales.

Usando Spring, no hemos tenido este problema, pero con Jakarta y Wildfly (Hibernate 6) si lo tenemos.
- Usamos @ManyToMany y @Entity sobre la misma tabla (ejemplar_prestamo).
- Si JPA intenta cargar Prestamo.getEjemplaresPrestados(), ignorará completamente los atributos adicionales como fechaLimiteDevolucion.
- Puede haber conflictos al persistir, borrar o hacer joins.
- Hibernate no puede gestionar dos formas distintas de mapear la misma relación.

___

Subo una versión nueva del proyecto con los entity Ejemplar y Prestamo nuevos.

Se ha eliminado la relación @ManyToMany y se ha creado la relación @OneToMany con EjemplarPrestamo.

Además el código del método save de PrestamoBean ha cambiado:

```
    public String save(){
        System.out.println("******** CREAR PRÉSTAMO *******");

        try {
            // 1. Crear el entity préstamo
            Prestamo prestamoNuevo = new Prestamo();
            System.out.println("* Socio id seleccionado: "+socioIdSeleccionado);

            // PENDIENTE: Comprobar que el socioIdSeleccionado es distinto de null y no está vacío
            // si es null o vacío redirigir a página de error  o misma página con un mensaje...

            Optional<Socio> socio = socioRepository.selectById(socioIdSeleccionado.intValue());

            System.out.println("* Socio seleccionado: "+socio);

            if (socio.isPresent()){
                prestamoNuevo.setSocio(socio.get());
                // 2. Crear el préstamo en la base de datos para obtener su id
                prestamoRepository.save(prestamoNuevo);
            }

            System.out.println("\t * Prestamo nuevo:"+prestamoNuevo);
            
            System.out.println("\n ******* LISTADO DE EJEMPLARES:");
            // Asociar los ejemplares seleccionados al préstamo
            for (Map.Entry<Long,Boolean> entry : seleccionados.entrySet()) {
                System.out.println("* key: "+entry.getKey()); // Long (identificador)
                System.out.println("* Value:"+entry.getValue()); // Boolean

                // Si es true, es que se ha seleccionado
                if (entry.getValue()){
                    Optional<Ejemplar> ejOptional = ejemplarRepository.selectById(entry.getKey().intValue());

                    if (ejOptional.isPresent()){
                        Ejemplar ejemplar = ejOptional.get();

                        // // Añadir el ejemplar al préstamo
                        // prestamoNuevo.addEjemplar(ejemplar);

                        // // Crear la entidad de la tabla intermedia PRESTAMO_EJEMPLAR
                        // // Lo creo porque tiene campos intermedios!!!
                        // EjemplarPrestamo ep = new EjemplarPrestamo(prestamoNuevo, ejemplar);

                        // Crear relación intermedia con atributos
                        EjemplarPrestamo ep = new EjemplarPrestamo(prestamoNuevo, ejemplar);

                        // Agregar la relación a ambas entidades
                        prestamoNuevo.addEjemplarPrestamo(ep);
                        ejemplar.getPrestamos().add(ep); // Asegúrate de tener este set en `Ejemplar`


                        System.out.println("\t ********** Ejemplar-Prestamo: "+ep);                        

                        // Guardar el EjemplarPrestamo en base de datos
                        //ejemplarPrestamoRepository.save(ep);

                    }

                    
                }
            }

            // Finalmente... salvar
        
            prestamoRepository.save(prestamoNuevo);
        } catch (JPAException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            // PENDIENTE!!!! GESTIONAR EL ERROR...

        }

        // se cargan todos los préstamos y debería aparecer el  nuevo creado
        return "prestamos.xhtml?faces-redirect=true";

    }
```









