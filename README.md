# Actividad Formativa BD FFEOE - RA4

Sistema de registro de partes de trabajo para una empresa técnica, desarrollado con Spring Boot y MariaDB.

Incluye:

- API REST para operaciones CRUD
- Interfaz web con Thymeleaf y Bootstrap
- Carga inicial de datos (`DataLoader`)
- Página de resumen de base de datos (`/bd`)
- Script SQL de vistas para phpMyAdmin (`vistas.sql`)

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
- Base de datos: `partes_trabajo_db`

## Vistas SQL para demostración (MariaDB)

Se incluye el archivo:

- `src/main/resources/sql/vistas.sql`

Este archivo crea tres vistas:

- `vista_partes_completas`
- `vista_materiales_usados`
- `vista_coste_partes`

### Cómo ejecutarlas en phpMyAdmin

1. Entra en `http://localhost:8080`.
2. Selecciona la base `partes_trabajo_db`.
3. Abre la pestaña `SQL`.
4. Copia y ejecuta el contenido de `src/main/resources/sql/vistas.sql`.

### Consultas de ejemplo

```sql
SELECT * FROM vista_partes_completas;
SELECT * FROM vista_materiales_usados;
SELECT * FROM vista_coste_partes;
```

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
- http://localhost:8089/bd

### Resumen visual de base de datos (`/bd`)

La pantalla `Base de datos` muestra:

- Información de conexión (MariaDB, base, puertos y phpMyAdmin)
- Contadores de registros (clientes, técnicos, materiales, partes y materiales usados)
- Tablas resumen de:
  - clientes
  - técnicos
  - materiales
  - partes de trabajo (con cliente y técnico)
  - materiales usados (con coste total calculado)
- Instrucciones de comprobación en phpMyAdmin
- Bloques con consultas SQL útiles para demo

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
- Navegación por la interfaz web:
  - menú principal (`/`)
  - clientes, técnicos, materiales, partes y materiales usados
  - resumen de base de datos (`/bd`)
- Demostración de CRUD web:
  - crear/editar/eliminar cliente
  - crear/editar/eliminar material
  - crear/editar/eliminar parte de trabajo
  - crear/eliminar material usado
- Tablas creadas en `partes_trabajo_db` y datos de prueba cargados automáticamente
- Comprobación de API REST (`/api/clientes`, `/api/partes`)
- Ejecución opcional de `vistas.sql` en phpMyAdmin y consulta de vistas:
  - `vista_partes_completas`
  - `vista_materiales_usados`
  - `vista_coste_partes`

## Verificación y pruebas

Se han añadido pruebas automáticas mínimas para validar servicios y API REST sin depender de MariaDB real.

Casos de prueba implementados:

1. `ClienteServiceTest`: listado de clientes (`listar`) devuelve datos del repositorio.
2. `ClienteServiceTest`: actualización de cliente (`actualizar`) modifica campos y guarda cambios.
3. `MaterialServiceTest`: creación de material (`crear`) persiste correctamente.
4. `MaterialServiceTest`: actualización de material (`actualizar`) modifica nombre, descripción, precio y stock.
5. `ParteTrabajoServiceTest`: actualización de parte (`actualizar`) modifica campos y relaciones (`cliente`, `tecnico`).
6. `ClienteControllerIntegrationTest` (MockMvc): `GET /api/clientes` responde `200 OK` y JSON esperado.

### Cómo ejecutar las pruebas

```bash
mvn test
```

Las pruebas de integración REST usan `MockMvc` con `@WebMvcTest`, por lo que no requieren conexión a MariaDB ni contenedores Docker para ejecutarse.

### Depuración

Si alguna prueba falla:

1. Revisa el primer error real en el log de Maven (ignora cascadas posteriores).
2. Ejecuta solo la clase afectada para iterar más rápido:

```bash
mvn -Dtest=ClienteServiceTest test
mvn -Dtest=ClienteControllerIntegrationTest test
```

3. Si falla un test de serialización JSON, verifica nombres de campos en entidades/controladores.
4. Si falla un test de servicio, revisa el `when(...).thenReturn(...)` del mock y el `verify(...)` esperado.
