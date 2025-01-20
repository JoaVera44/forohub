# Foro Hub

## Descripción

Foro Hub es un proyecto backend diseñado para gestionar tópicos de discusión mediante una API REST desarrollada con el framework Spring. Este programa está centrado en implementar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para diversos endpoints, siguiendo las mejores prácticas en el desarrollo de APIs.

## Funcionalidades Principales

Nuestra API REST incluye las siguientes características:

1. **Gestión de Tópicos**:

   - Crear un nuevo tópico.
   - Mostrar todos los tópicos creados.
   - Mostrar un tópico específico por ID.
   - Actualizar un tópico.
   - Eliminar un tópico.

2. **Extensiones Adicionales**:

   - Listar tópicos por ID del autor.

3. **Gestión de Usuarios y Respuestas**:

   - Implementación de métodos CRUD para los endpoints `/usuarios` y `/respuestas`.

4. **Seguridad y Autenticación**:

   - Implementación de seguridad con Spring Security.
   - Uso de tokens JWT para autenticación y autorización.
   - Contraseñas hasheadas para mayor seguridad.

5. **Documentación**:

   - Integración con SpringFox Swagger para generar documentación interactiva de la API.

6. **Base de Datos**:

   - Persistencia de datos mediante la implementación de una base de datos relacional utilizando MySQL.

7. **Validaciones**:

   - Validaciones implementadas según reglas de negocio definidas.

## Tecnologías Utilizadas

- **Java**
- **Spring Boot**
- **Spring Security**
- **SpringFox Swagger**
- **JWT (JSON Web Tokens)**
- **MySQL**

##Endpoints Disponibles
## Tópicos
- POST /topicos - Crear un nuevo tópico.
- GET /topicos - Listar todos los tópicos.
- GET /topicos/{id} - Consultar un tópico por ID.
- GET /topicos/autor/{Id} - Listar tópicos por ID del autor.
- PUT /topicos/{id} - Actualizar un tópico.
- DELETE /topicos/{id} - Eliminar un tópico.
  
## Usuarios
- POST /usuarios - Crear un nuevo usuario.
- GET /usuarios - Listar todos los usuarios.
- PUT /usuarios/{id} - Actualizar un usuario.
- DELETE /usuarios/{id} - Eliminar un usuario.

## Respuestas
- POST /respuestas - Crear una nueva respuesta.
- GET /respuestas - Listar todas las respuestas.
- PUT /respuestas/{id} - Actualizar una respuesta.
- DELETE /respuestas/{id} - Eliminar una respuesta.
  
# Seguridad
La autenticación y autorización del sistema se realizan **mediante JWT** y se aseguran con **Spring Security**. A continuación, algunos detalles clave:

- Los tokens JWT protegen las rutas restringidas.
- Las contraseñas se almacenan usando hashing seguro para prevenir accesos no autorizados.
  
# Documentación Interactiva
La documentación de la API está disponible a través de Swagger entrando al endpoint
/swagger-ui.html


