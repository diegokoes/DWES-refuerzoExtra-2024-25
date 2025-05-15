# Explicación JPA 

![image](https://github.com/user-attachments/assets/cc522ed5-4bc7-48da-873a-631de9b24987)

## Relación OneToOne entre Autor y Direccion

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

![image](https://github.com/user-attachments/assets/65c693f8-642a-41ec-809a-b76dd612e38b)


