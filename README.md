# Sistema de Inventario Automotriz - Nexos Software

Este proyecto consiste en una aplicación web para la gestión de mercancía del sector automotriz. Fue desarrollado como parte de una prueba técnica para Nexos Software, cumpliendo con buenas prácticas de arquitectura, pruebas automatizadas y scripts reprocesables.

---

## Estructura del repositorio

El repositorio está estructurado de la siguiente manera para facilitar la organización y el mantenimiento del código:

- `inventario-automotriz-backend/` – Proyecto Spring Boot
- `inventario-automotriz-frontend/` – Proyecto Angular
- `scripts-sql/` – Scripts SQL de despliegue y validación
- `Evidencias/` – Capturas y documentación de pruebas
- `README.md` – Documentación principal del proyecto

---

## Instrucciones rápidas

### 1. Base de datos

Ejecutar los siguientes scripts **en orden** desde la carpeta `/scripts-sql/`:

1. `01a_drop_database_if_exists.sql`
2. `01b_create_database.sql`
3. `02_init_schema.sql`
4. `03_insert_sample_data.sql`

### 2. Configuración del backend (Spring Boot)

Ajusta el archivo `application.properties` con la configuración adecuada para tu entorno:

```properties
# Nombre lógico de la aplicación
spring.application.name=inventario-automotriz-nexos

# Configuración de la base de datos PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/inventario_automotriz
spring.datasource.username=postgres
spring.datasource.password=1234

# Configuración de JPA / Hibernate
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Puerto de ejecución
server.port=8080
```

> Asegúrate de que PostgreSQL esté en ejecución y que las credenciales coincidan con tu entorno.

### 3. Ejecución de la aplicación

**Backend:**

```bash
./mvnw spring-boot:run
```

**Frontend (Angular):**

```bash
npm install
ng serve
```

Accede a la aplicación en: [http://localhost:4200](http://localhost:4200)

---

## Pruebas unitarias y de integración

Como parte del cumplimiento de buenas prácticas, se desarrollaron y ejecutaron pruebas automatizadas con JUnit 5, Mockito y Spring Boot.

### 1. Prueba unitaria: `MercanciaServiceImplTest`

Valida la lógica de negocio del servicio de mercancía:

- Registro correcto.
- Validación de existencia previa.
- Asociación del usuario que registra.

Evidencias:

![Prueba exitosa](./Evidencias/test-unitarios/mercancia_service_impl_test_ok.png)

---

### 2. Prueba de controlador: `MercanciaControllerTest` (@WebMvcTest)

Simula una petición `POST /api/mercancia` para validar:

- Funcionamiento del endpoint.
- Validaciones sobre el JSON de entrada.
- Respuesta con status HTTP y cuerpo correcto.

Evidencias:

![Prueba exitosa](./Evidencias/test-integracion/mercancia_controller_test_ok.png)

---

### 3. Prueba de integración: `MercanciaIntegrationTest` (@SpringBootTest)

Valida el flujo completo de registro de mercancía, incluyendo:

- Persistencia de `Cargo`, `Usuario` y `Mercancia`.
- Integración entre DTOs, entidades, servicios y repositorios.

Evidencias disponibles en la carpeta `/Evidencias`.

---

## Resumen de pruebas realizadas

| Tipo de prueba      | Clase                            | Tecnología               |
|---------------------|----------------------------------|--------------------------|
| Unitaria            | `MercanciaServiceImplTest`       | JUnit + Mockito          |
| Controlador (Mock)  | `MercanciaControllerTest`        | @WebMvcTest              |
| Integración total   | `MercanciaIntegrationTest`       | @SpringBootTest          |

Las pruebas se ejecutaron correctamente en un entorno con **Java 17+** y **PostgreSQL**, como puede observarse en la carpeta [`/Evidencias`](./Evidencias/).

---

## Scripts de base de datos

Los siguientes scripts están ubicados en la carpeta `/scripts-sql/`:

| Script                           | Descripción                                                                 |
|----------------------------------|-----------------------------------------------------------------------------|
| 01a_drop_database_if_exists.sql  | Elimina la base de datos si existe (ideal para pruebas locales).            |
| 01b_create_database.sql          | Crea la base de datos `inventario_nexos`.                                   |
| 02_init_schema.sql               | Crea las tablas `cargo`, `usuarios`, `mercancia` con sus relaciones.        |
| 03_insert_sample_data.sql        | Inserta datos de ejemplo (cargos y usuarios).                               |
| 04_select_queries.sql            | Consultas de validación manual.                                             |
| 05_validation_cases.sql          | Reglas de negocio y pruebas adicionales.                                    |

> Todos los scripts son **reprocesables**, permitiendo reiniciar el estado de la base de datos fácilmente.

---

## Tecnologías utilizadas

- Java 17+
- Spring Boot 3.5.3
- Spring Security
- Spring Web
- Spring Boot DevTools
- Lombok
- Maven
- PostgreSQL
- Angular
- JUnit 5, Mockito
- Hibernate / Spring Data JPA

---

¡Gracias por revisar este proyecto!