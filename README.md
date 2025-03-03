# API REST - Ecommerce de Ordenadores

## Descripción

Esta API ha sido desarrollada con Spring Boot y gestiona exclusivamente las solicitudes a la base de datos. Se encarga de la persistencia de datos sin manejar lógica de negocio, ya que esta última se administra en el Dynamic Web Project. La API provee funcionalidades de usuarios, productos,  pedidos y reseñas, además de gestionar el proceso de autenticación y recuperación de contraseñas mediante verificación por correo electrónico.

## Tecnologías Utilizadas

- **Spring Boot** (Backend)
- **Spring Data JPA** (Persistencia)
- **Spring Security** (Autenticación y autorización)
- **PostgreSQL** (Base de datos)
- **Java 17**

## Funcionalidades

### Administrador

- CRUD de usuarios (crear, leer, actualizar y eliminar usuarios).
- CRUD de productos (crear, leer, actualizar y eliminar productos desde el panel de administración).

### Usuarios

- Registro de usuario con verificación de correo electrónico mediante código de confirmación.
- Iniciar sesión.
- Recuperación de contraseña a través de correo electrónico.

### Productos

- Listado de productos disponibles.
- Visualización de detalles de un producto.
- Agregar reseñas a un producto.

### Carrito de Compras

- Agregar productos al carrito.
- Eliminar productos del carrito.

### Pedidos

- Realizar un pedido registrando los productos del carrito y los datos de pago.

## Validaciones y Seguridad

- Todas las validaciones (formatos de datos, restricciones de negocio, etc.) son manejadas por el Dynamic Web Project.
- La API solo recibe, almacena y consulta información de la base de datos.

## Estructura del Proyecto

El proyecto está organizado en las siguientes capas:

- **Controladores**: Manejan las solicitudes HTTP.
- **Servicios**: Gestionan la lógica de acceso a los datos.
- **Repositorios**: Realizan la comunicación con la base de datos mediante JPA.
- **Entidades**: Representan las tablas de la base de datos.

## Autor

**David Muñoz-Polanco Muñoz**

