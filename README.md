# API de Gestión de Productos Reactiva

Este proyecto es una API REST reactiva construida con Spring Boot y MongoDB para la gestión de productos.

## Requisitos Previos

- JDK 17
- Maven
- MongoDB
- IDE (IntelliJ IDEA o VS Code)

## Estructura del Proyecto

El proyecto sigue una arquitectura en capas:

- `model`: Contiene la entidad Producto
- `repository`: Contiene el repositorio reactivo para MongoDB
- `service`: Contiene la lógica de negocio
- `controller`: Contiene los endpoints REST

## Pruebas

El proyecto incluye dos tipos de pruebas:

### Pruebas Unitarias (ProductoServiceTest)

Estas pruebas utilizan Mockito para simular el comportamiento del repositorio y verificar la lógica del servicio.

Para ejecutar solo las pruebas unitarias:
```bash
./mvnw test -Dtest=ProductoServiceTest
```

### Pruebas de Integración (ProductoIntegrationTest)

Estas pruebas utilizan WebTestClient para probar los endpoints REST con una base de datos MongoDB real.

Para ejecutar solo las pruebas de integración:
```bash
./mvnw test -Dtest=ProductoIntegrationTest
```

### Ejecutar Todas las Pruebas

Para ejecutar todas las pruebas:
```bash
./mvnw test
```

## CI/CD con GitHub Actions

El proyecto incluye un pipeline de CI/CD que se ejecuta automáticamente en GitHub Actions cuando:

- Se hace push a la rama main
- Se crea un pull request hacia main

El pipeline:
1. Configura el entorno (JDK 17, MongoDB)
2. Ejecuta todas las pruebas
3. Notifica el resultado en el pull request

## Endpoints API

- GET /api/productos - Lista todos los productos
- GET /api/productos/{id} - Obtiene un producto por ID
- POST /api/productos - Crea un nuevo producto
- DELETE /api/productos/{id} - Elimina un producto 