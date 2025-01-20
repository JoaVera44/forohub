-- Crear tabla Usuario
CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) UNIQUE,
    correo VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    perfil VARCHAR(255)
);

-- Crear tabla Curso
CREATE TABLE cursos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL
);

-- Crear tabla Topico
CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    status BOOLEAN NOT NULL,
    usuarios_id BIGINT NOT NULL,
    cursos_id BIGINT NOT NULL,
    CONSTRAINT fk_topicos_usuarios FOREIGN KEY (usuarios_id) REFERENCES usuarios(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_topicos_cursos FOREIGN KEY (cursos_id) REFERENCES cursos(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Crear tabla Respuesta
CREATE TABLE respuestas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensaje TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    solucion TEXT NOT NULL,
    usuarios_id BIGINT NOT NULL,
    topicos_id BIGINT NOT NULL,
    CONSTRAINT fk_respuestas_usuarios FOREIGN KEY (usuarios_id) REFERENCES usuarios(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_respuestas_topicos FOREIGN KEY (topicos_id) REFERENCES topicos(id) ON DELETE CASCADE ON UPDATE CASCADE
);