Este proyecto consiste en el desarrollo de una aplicación de comercio electrónico (e-commerce) especializada en la venta de productos de computación como hardware (procesadores, tarjetas gráficas, memorias RAM, discos duros, etc.), utilizando Next.js para el frontend y Spring Boot para el backend. La aplicación permite gestionar productos, usuarios y pedidos, ofreciendo una experiencia de compra fluida y segura.


- Gestión de productos: Agregar, editar y eliminar productos.
- Autenticación de usuarios: Registro, inicio de sesión y gestión de cuentas de usuarios mediante JWT y 
Spring Security para proteger las rutas y asegurar la aplicación.
- Carrito de compras: Los usuarios pueden añadir productos al carrito y proceder con el pago.
- Procesamiento de pedidos: Los usuarios pueden realizar pedidos que se almacenan en la base de datos.

Tecnologías Utilizadas

- Frontend:  Next.js (basado en React), TypeScript, CSS, Sass
- Backend: Spring Boot
- Base de Datos: MySQL

Arquitectura
El proyecto sigue una Arquitectura Hexagonal, separando las preocupaciones del dominio, adaptadores y puertos para facilitar la extensión y mantenimiento del sistema.

Microservicios y Eureka
Usa Eureka de Spring Cloud para el descubrimiento de servicios, permitiendo que los microservicios se registren y descubran dinámicamente.

Pruebas Unitarias
Se ha integrado JaCoCo para medir la cobertura de pruebas unitarias, asegurando la calidad y estabilidad del código.