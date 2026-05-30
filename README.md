# Actividad Formativa BD FFEOE - RA4

Sistema de registro de partes de trabajo para una empresa técnica, desarrollado con Spring Boot y MariaDB.

## Requisitos

- Java 21
- Maven 3.9+
- Docker y Docker Compose

## Docker usado

Servicios necesarios del `docker-compose`:

- `db` (MariaDB 10.11)
- `phpmyadmin`

## Levantar MariaDB y phpMyAdmin

```bash
docker compose up -d db phpmyadmin
```

phpMyAdmin:

- URL: http://localhost:8080
- Servidor: `db` o `localhost` según entorno
- Usuario: `root`
- Contraseña: `rootpass`

## Arrancar Spring Boot

```bash
mvn spring-boot:run
```

- Puerto aplicación: `8089`

## Configuración de base de datos

La aplicación se conecta a:

- URL JDBC: `jdbc:mariadb://localhost:3308/partes_trabajo_db?createDatabaseIfNotExist=true&serverTimezone=UTC`
- Usuario: `root`
- Contraseña: `rootpass`

## Endpoints principales

- `GET/POST /api/clientes`
- `GET/PUT/DELETE /api/clientes/{id}`
- `GET/POST /api/tecnicos`
- `GET/PUT/DELETE /api/tecnicos/{id}`
- `GET/POST /api/materiales`
- `GET/PUT/DELETE /api/materiales/{id}`
- `GET/POST /api/partes`
- `GET/PUT/DELETE /api/partes/{id}`
- `GET/POST /api/parte-materiales`
- `GET/PUT/DELETE /api/parte-materiales/{id}`

Los endpoints REST siguen disponibles bajo `/api`.

## Interfaz web (Thymeleaf)

URLs web:

- http://localhost:8089/
- http://localhost:8089/clientes
- http://localhost:8089/tecnicos
- http://localhost:8089/materiales
- http://localhost:8089/partes
- http://localhost:8089/parte-materiales

## Ejemplos JSON

### Crear Cliente

```json
{
  "nombre": "Nuevo Cliente",
  "empresa": "Empresa Demo",
  "email": "cliente@demo.com",
  "telefono": "600000000"
}
```

### Crear Tecnico

```json
{
  "nombre": "Tecnico Demo",
  "email": "tecnico@demo.com",
  "especialidad": "Soporte"
}
```

### Crear Material

```json
{
  "nombre": "Latiguillo",
  "descripcion": "Cable de red corto",
  "precioUnitario": 3.25,
  "stock": 30
}
```

### Crear ParteTrabajo

```json
{
  "titulo": "Incidencia de red",
  "descripcion": "Caida intermitente",
  "fecha": "2026-05-30",
  "horasTrabajadas": 2.0,
  "estado": "EN PROCESO",
  "cliente": { "id": 1 },
  "tecnico": { "id": 1 }
}
```

### Crear ParteMaterial

```json
{
  "cantidad": 2,
  "parteTrabajo": { "id": 1 },
  "material": { "id": 1 }
}
```

## Qué mostrar en el vídeo demo

- Arranque de Docker (`db` y `phpmyadmin`)
- Arranque de Spring Boot en puerto `8089`
- Tablas creadas en `partes_trabajo_db`
- Datos de prueba cargados automáticamente
- Prueba CRUD de al menos un endpoint (por ejemplo `/api/clientes`)
- Relación entre `parte_trabajo` y `parte_material`
