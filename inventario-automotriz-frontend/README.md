# Inventario Automotriz - Frontend

Este proyecto corresponde al **frontend del sistema de inventario automotriz** desarrollado como parte de una prueba técnica para Nexos Software. Está construido con **Angular 17** y se comunica con el backend desarrollado en Spring Boot a través de servicios REST.

## Requisitos previos

* Node.js 18+
* Angular CLI (`npm install -g @angular/cli`)
* Backend corriendo en `http://localhost:8080` (ver carpeta `inventario-automotriz-backend/`)

## Ejecución del proyecto

1. Instala las dependencias:

```bash
npm install
```

2. Ejecuta el servidor de desarrollo:

```bash
ng serve
```

3. Abre el navegador en:

```
http://localhost:4200/
```

La aplicación se recargará automáticamente al detectar cambios.

## Estructura principal del frontend

* `src/app/components`: Componentes principales como `listar-mercancia`, `registrar-mercancia`, etc.
* `src/app/services`: Servicios que manejan la comunicación con la API REST (`mercancia.service.ts`)
* `src/app/models`: Interfaces TypeScript para las entidades (`Usuario`, `Mercancia`, etc.)

## Integración con el Backend

Este frontend espera consumir los siguientes endpoints:

* `GET /api/mercancia`
* `POST /api/mercancia`
* `PUT /api/mercancia/{id}`
* `DELETE /api/mercancia/{id}`

Asegúrate de que el backend esté en ejecución antes de utilizar la aplicación.

## Pruebas (opcional)

Este proyecto aún no incluye pruebas unitarias específicas, pero puedes ejecutarlas con:

```bash
ng test
```

## Comandos útiles

* Generar un componente:

```bash
ng generate component nombre-componente
```

* Generar un servicio:

```bash
ng generate service nombre-servicio
```

## Recursos adicionales

* [Angular CLI – Documentación oficial](https://angular.dev/tools/cli)

---

> Advertencia: Recuerda configurar el CORS del backend en caso de que ejecutes el frontend y backend en puertos diferentes.
