-- Crear la tabla Categorias
CREATE TABLE Categorias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- Campo id como clave primaria autoincremental
    nombre VARCHAR(255) NOT NULL           -- Campo nombre
);

-- Insertar datos de ejemplo en la tabla Categorias
INSERT INTO Categorias (nombre) VALUES
('Electrónica'),
('Ropa'),
('Hogar'),
('Juguetes'),
('Libros');

-- Crear la tabla Productos
CREATE TABLE Productos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- Campo id como clave primaria autoincremental
    nombre VARCHAR(255) NOT NULL,          -- Campo nombre
    precio INT NOT NULL,                   -- Campo precio
    sku VARCHAR(100) NOT NULL UNIQUE,      -- Campo SKU único
    fecha_registro DATE NOT NULL,          -- Campo fechaRegistro (LocalDate en Java, equivalente a DATE en SQL)
    categoria_id BIGINT,                   -- Clave foránea que referencia a Categorias
    CONSTRAINT fk_categoria FOREIGN KEY (categoria_id) REFERENCES Categorias(id)
);

-- Insertar datos de ejemplo en la tabla Productos
INSERT INTO Productos (nombre, precio, sku, fecha_registro, categoria_id) VALUES
('Producto 1', 1000, 'SKU001', '2023-10-10', 1),  -- Electrónica
('Producto 2', 1500, 'SKU002', '2023-10-11', 2),  -- Ropa
('Producto 3', 2000, 'SKU003', '2023-10-12', 3),  -- Hogar
('Producto 4', 2500, 'SKU004', '2023-10-13', 4),  -- Juguetes
('Producto 5', 3000, 'SKU005', '2023-10-14', 5);  -- Libros

-- Consulta para verificar los datos con JOIN entre Productos y Categorias
SELECT p.*, c.nombre AS categoria 
FROM Productos AS p
INNER JOIN Categorias AS c ON p.categoria_id = c.id
ORDER BY p.id ASC;
