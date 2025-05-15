# Explicación JPA 

## Relación OneToOne entre Autor y Direccion

Relación bidireccional (autor-dirección):
- Un autor tiene una dirección. 
- Una dirección pertenece a un único autor.

![image](https://github.com/user-attachments/assets/cc522ed5-4bc7-48da-873a-631de9b24987)

**En el entity Autor:**
```
@OneToOne
@JoinColumn(name = "direccion_id")
private Direccion direccion;
```

**En el entity Direccin:**

```
@OneToOne(mappedBy = "direccion")
private Autor autor;
```

## Relación OneToMany / ManyToOne entre Libro y Autor

Relación bidireccional (autor-libro):
- Un autor escribe muchos libros, por tanto muchos libros pueden ser escritos por el mismo autor.
- Un libro es escrito por un único autor. 

![image](https://github.com/user-attachments/assets/65c693f8-642a-41ec-809a-b76dd612e38b)

![image](https://github.com/user-attachments/assets/9bca5d4a-9434-41e6-8b3a-91b43d0256dc)


**En el entity Autor:**

```
@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
private Set<Libro> libros;
```

**En el entity Libro:**

```
@ManyToOne
@JoinColumn(name = "autor_id")
private Autor autor;
```




