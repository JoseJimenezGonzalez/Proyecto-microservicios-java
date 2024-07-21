# Proyecto de Microservicios en Java
Este repositorio contiene un conjunto de microservicios desarrollados en Java, diseñados siguiendo las mejores prácticas de desarrollo, principios SOLID, y una arquitectura limpia con separación de capas. Utiliza tecnologías modernas como JPA, Eureka Server, Spring Gateway, y más para crear un ecosistema robusto y escalable.

## Tecnologías Utilizadas
- Spring Boot: Framework principal para la creación de microservicios.
- Spring Data JPA: Para la capa de persistencia y manejo de datos.
- Spring Cloud Eureka: Para el descubrimiento de servicios.
- Spring Cloud Gateway: Para la gestión de la API Gateway.
- MySQL / H2: Bases de datos para almacenar la información de los servicios.
## Arquitectura del Proyecto
El proyecto sigue una arquitectura de microservicios, donde cada servicio es responsable de una funcionalidad específica y se comunica con otros servicios a través de APIs RESTful. Por motivos de conveniencia y simplicidad, todos los microservicios comparten una única base de datos MySQL.

## Estructura de Microservicios
- Commons Entity: Contiene las entidades comunes que son compartidas entre los diferentes microservicios.
- Commons Utils: Contiene utilidades y validaciones comunes que pueden ser utilizadas por los diferentes microservicios.
- Eureka Server: Actúa como servidor de registro y descubrimiento de servicios, permitiendo que los microservicios se registren y descubran entre sí.
- Gateway Server: Utiliza Spring Cloud Gateway para manejar todas las solicitudes entrantes, realizar el balanceo de carga y enrutar las solicitudes al microservicio adecuado.
- Microservicio Cursos: -- Gestiona la información de los cursos. - Realiza operaciones CRUD sobre los cursos. - Utiliza Spring Data JPA para interactuar con la base de datos de cursos.
- Microservicio Exámenes: - Gestiona la información de los exámenes. - Realiza operaciones CRUD sobre los exámenes. - Utiliza Spring Data JPA para interactuar con la base de datos de exámenes.
- Microservicio Respuestas: - Gestiona la información de las respuestas de los estudiantes. - Realiza operaciones CRUD sobre las respuestas. - Utiliza Spring Data JPA para interactuar con la base de datos de respuestas.
- Microservicio Usuarios: - Gestiona la información de los usuarios. - Realiza operaciones CRUD sobre los usuarios. - Utiliza Spring Data JPA para interactuar con la base de datos de usuarios.
## Principios y Buenas Prácticas
- Principios SOLID: Cada microservicio sigue los principios SOLID para asegurar un diseño limpio y mantenible.
- Separación de Capas: Cada microservicio está estructurado en capas (Controlador, Servicio, Repositorio) para una clara separación de responsabilidades.
- Inyección de Dependencias: Utilizamos Spring para gestionar la inyección de dependencias, promoviendo un código desacoplado y fácil de probar.
##Comunicación entre Microservicios
- Feign Client: Los microservicios utilizan Feign Client para realizar llamadas a otros servicios de manera sencilla y declarativa. Esto facilita la comunicación y reduce la complejidad del código.
