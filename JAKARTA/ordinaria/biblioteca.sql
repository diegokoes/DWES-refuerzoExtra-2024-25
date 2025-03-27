DROP TABLE IF EXISTS ejemplar_prestamo;
DROP TABLE IF EXISTS ejemplar;
DROP TABLE IF EXISTS libro;
DROP TABLE IF EXISTS prestamo;
DROP TABLE IF EXISTS socio;

CREATE TABLE socio (
    id IDENTITY PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefono VARCHAR(50),
    fecha_registro DATE
);

CREATE TABLE prestamo (
    id IDENTITY PRIMARY KEY,
    socio_id BIGINT NOT NULL,
    fecha_prestamo DATE NOT NULL,
    FOREIGN KEY (socio_id) REFERENCES socio(id) ON DELETE CASCADE
);

CREATE TABLE libro (
    titulo VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) PRIMARY KEY,
    editorial VARCHAR(255),
    autor VARCHAR(255)
);

CREATE TABLE ejemplar (
    id IDENTITY PRIMARY KEY,
    libro_isbn VARCHAR(20) NOT NULL,
    codigo_inventario VARCHAR(100) UNIQUE,
    FOREIGN KEY (libro_isbn) REFERENCES libro(isbn) ON DELETE CASCADE
);

CREATE TABLE ejemplar_prestamo (
    prestamo_id BIGINT NOT NULL,
    ejemplar_id BIGINT NOT NULL,
    fecha_limite_devolucion DATE NOT NULL,
    fecha_real_devolucion DATE,
    PRIMARY KEY (prestamo_id, ejemplar_id),
    FOREIGN KEY (prestamo_id) REFERENCES prestamo(id) ON DELETE CASCADE,
    FOREIGN KEY (ejemplar_id) REFERENCES ejemplar(id) ON DELETE CASCADE
);

-- Insertar socios
INSERT INTO socio (nombre, email, telefono, fecha_registro) VALUES 
('Ana Gómez', 'ana@example.com', '600123456', CURRENT_DATE),
('Luis Pérez', 'luis@example.com', '600654321', CURRENT_DATE),
('Carla Ruiz', 'carla@example.com', '600789123', CURRENT_DATE),
('Jorge León', 'jorge@example.com', '600321789', CURRENT_DATE);

-- Insertar libros
INSERT INTO libro (titulo, isbn, editorial, autor) VALUES
('Cien Años de Soledad', '978-3-16-148410-0', 'Sudamericana', 'Gabriel García Márquez'),
('1984', '978-0-452-28423-4', 'Secker & Warburg', 'George Orwell'),
('El Principito', '978-0156012195', 'Reynal & Hitchcock', 'Antoine de Saint-Exupéry'),
('Don Quijote de la Mancha', '978-84-376-0494-7', 'Alfaguara', 'Miguel de Cervantes'),
('Rayuela', '978-84-376-0495-4', 'Sudamericana', 'Julio Cortázar');

-- Insertar ejemplares
INSERT INTO ejemplar (libro_isbn, codigo_inventario) VALUES
('978-3-16-148410-0', 'CA001'), ('978-3-16-148410-0' ,'CA002'),
('978-0-452-28423-4', '1984A'), ('978-0-452-28423-4', '1984B'),
('978-0156012195', 'PRIN01'),
('978-84-376-0494-7', 'DQ01'), ('978-84-376-0494-7', 'DQ02'),
('978-84-376-0495-4', 'RAY01');

-- Insertar préstamos
INSERT INTO prestamo (socio_id, fecha_prestamo) VALUES
(1, CURRENT_DATE),
(2, CURRENT_DATE - 3),
(3, CURRENT_DATE - 5),
(4, CURRENT_DATE - 1);

-- Insertar líneas de préstamo
INSERT INTO ejemplar_prestamo (prestamo_id, ejemplar_id, fecha_limite_devolucion, fecha_real_devolucion) VALUES
(1, 1, CURRENT_DATE + 15, NULL),
(1, 3, CURRENT_DATE + 15, NULL),
(2, 2, CURRENT_DATE + 12, CURRENT_DATE),
(2, 4, CURRENT_DATE + 12, NULL),
(3, 5, CURRENT_DATE + 10, NULL),
(3, 6, CURRENT_DATE + 10, NULL),
(4, 8, CURRENT_DATE + 14, NULL);
