-- Insertar Cursos con descripción
INSERT INTO curso (nombre, descripcion) VALUES ('1DAW', '1º curso de Desarrollo de Aplicaciones Web');
INSERT INTO curso (nombre, descripcion) VALUES ('2DAW', '2º curso de Desarrollo de Aplicaciones Web');

-- Insertar Evaluaciones asociadas a los cursos (códigos únicos)
INSERT INTO evaluacion (codigo, nombre, curso_id) VALUES ('1ev_1', 'Primera Evaluación', 1);
INSERT INTO evaluacion (codigo, nombre, curso_id) VALUES ('2ev_1', 'Segunda Evaluación', 1);
INSERT INTO evaluacion (codigo, nombre, curso_id) VALUES ('3ev_1', 'Tercera Evaluación', 1);
INSERT INTO evaluacion (codigo, nombre, curso_id) VALUES ('ordinaria_1', 'Ordinaria', 1);
INSERT INTO evaluacion (codigo, nombre, curso_id) VALUES ('extraordinaria_1', 'Extraordinaria', 1);

INSERT INTO evaluacion (codigo, nombre, curso_id) VALUES ('1ev_2', 'Primera Evaluación', 2);
INSERT INTO evaluacion (codigo, nombre, curso_id) VALUES ('2ev_2', 'Segunda Evaluación', 2);
INSERT INTO evaluacion (codigo, nombre, curso_id) VALUES ('ordinaria_2', 'Ordinaria', 2);
INSERT INTO evaluacion (codigo, nombre, curso_id) VALUES ('extraordinaria_2', 'Extraordinaria', 2);

-- Insertar Notas para estudiantes en 1DAW
INSERT INTO nota (nia, evaluacion_id, calificacion) VALUES ('12345', 1, 8);
INSERT INTO nota (nia, evaluacion_id, calificacion) VALUES ('12346', 1, 7);
INSERT INTO nota (nia, evaluacion_id, calificacion) VALUES ('12347', 1, 6);
INSERT INTO nota (nia, evaluacion_id, calificacion) VALUES ('12348', 2, 9);
INSERT INTO nota (nia, evaluacion_id, calificacion) VALUES ('12345', 2, 5);
INSERT INTO nota (nia, evaluacion_id, calificacion) VALUES ('12346', 3, 4);
INSERT INTO nota (nia, evaluacion_id, calificacion) VALUES ('12348', 4, 8);
INSERT INTO nota (nia, evaluacion_id, calificacion) VALUES ('12345', 5, 7);
INSERT INTO nota (nia, evaluacion_id, calificacion) VALUES ('12346', 5, 6);
INSERT INTO nota (nia, evaluacion_id, calificacion) VALUES ('12347', 5, 5);

-- Insertar Notas para 1ev_2 y 2ev_2 en 2DAW
INSERT INTO nota (nia, evaluacion_id, calificacion) VALUES ('12400', 6, 7);
INSERT INTO nota (nia, evaluacion_id, calificacion) VALUES ('12401', 6, 9);
INSERT INTO nota (nia, evaluacion_id, calificacion) VALUES ('12402', 6, 6);
INSERT INTO nota (nia, evaluacion_id, calificacion) VALUES ('12400', 7, 8);
INSERT INTO nota (nia, evaluacion_id, calificacion) VALUES ('12401', 7, 7);
INSERT INTO nota (nia, evaluacion_id, calificacion) VALUES ('12402', 7, 5);

-- ROLES Y USUARIOS DE EJEMPLO!!!!!
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_PROFESOR');

INSERT INTO users (username, password) VALUES ('admin', '$2a$10$Ey/H5tNIopwhtVYXQ76Ms.oeiol3A4NiG3/HJekFyKkmgLVbR1n1C');
INSERT INTO users (username, password) VALUES ('user', '$2a$10$XVgKh.17he10CTo6Av57xOlSpnQWYxVJyshfkxjPKFLGTfth7FQZy');
INSERT INTO users (username, password) VALUES ('profesor', '$2a$10$Ey/H5tNIopwhtVYXQ76Ms.oeiol3A4NiG3/HJekFyKkmgLVbR1n1C');


INSERT INTO users_roles (role_id,user_id) VALUES(2,1);
INSERT INTO users_roles (role_id,user_id) VALUES(1,2);
INSERT INTO users_roles (role_id, user_id) VALUES (3, 3);
--INSERT INTO users_roles (role_id, user_id) VALUES (1, 3);

-- pendiente: añadir más roles a usuarios