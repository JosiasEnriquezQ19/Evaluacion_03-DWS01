# Blog Interno - Sistema de Gestión de Autores y Publicaciones

## 📋 Información del Proyecto

**Curso:** Desarrollo de Servicios Web 1
**Evaluación:** EVA-03
**Institución:** IDAT
**Semestre:** 5to Ciclo

## 🎯 Descripción

Sistema web para la gestión integral de un blog interno que permite administrar autores y sus publicaciones. Desarrollado con Spring Boot y tecnologías web modernas, ofrece una interfaz intuitiva para el control completo del flujo de trabajo editorial.

## ✨ Características Principales

### Gestión de Autores

- ✅ Registro y edición de autores
- ✅ Validación de datos (correo electrónico, nombre)
- ✅ Control de estado (Activo/Inactivo)
- ✅ Vista detallada con estadísticas

### Gestión de Publicaciones

- ✅ Creación y edición de publicaciones
- ✅ Validación en tiempo real
- ✅ Estados de publicación (Borrador, Publicado, Archivado)
- ✅ Relación con autores
- ✅ Control de fechas automático

### Funcionalidades Técnicas

- ✅ Validación frontend y backend
- ✅ Interfaz responsive con Bootstrap 5.3
- ✅ Manejo de errores personalizado
- ✅ Sistema de navegación intuitivo

## 🛠 Tecnologías Utilizadas

### Backend

- **Spring Boot 3.x** - Framework principal
- **Spring Data JPA** - Persistencia de datos
- **Spring Web MVC** - Controladores web
- **MySQL** - Base de datos
- **Thymeleaf** - Motor de plantillas
- **Jakarta Validation** - Validaciones

### Frontend

- **Bootstrap 5.3.0** - Framework CSS
- **Bootstrap Icons** - Iconografía
- **JavaScript ES6** - Interactividad
- **HTML5** - Estructura
- **CSS3** - Estilos personalizados

### Herramientas de Desarrollo

- **Maven** - Gestión de dependencias
- **Lombok** - Reducción de código boilerplate
- **Spring Boot DevTools** - Desarrollo rápido

## 📁 Estructura del Proyecto

```
eva03/
├── src/main/java/com/idat/eva03/
│   ├── controller/           # Controladores web
│   │   ├── AutorController.java
│   │   ├── MainController.java
│   │   └── CustomErrorController.java
│   ├── dto/                 # Objetos de transferencia de datos
│   ├── model/              # Entidades JPA
│   │   ├── Autores.java
│   │   └── Publicaciones.java
│   ├── repository/         # Repositorios de datos
│   │   ├── AutorRepository.java
│   │   └── PublicacionRepository.java
│   ├── service/           # Lógica de negocio
│   │   ├── AutorService.java
│   │   └── PublicacionService.java
│   └── util/             # Enumeraciones y utilidades
│       ├── EstadoAutor.java
│       └── EstadoPublicacion.java
├── src/main/resources/
│   ├── templates/         # Plantillas Thymeleaf
│   │   ├── autor/        # Vistas de autores
│   │   ├── publicacion/  # Vistas de publicaciones
│   │   ├── error/        # Páginas de error
│   │   └── fragments/    # Fragmentos reutilizables
│   ├── static/           # Recursos estáticos
│   └── application.properties
└── src/test/java/        # Pruebas unitarias
```

## 🔧 Configuración e Instalación

### Prerrequisitos

- JDK 17 o superior
- Maven 3.8+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Pasos de Instalación

1. **Clonar el proyecto**

```bash
git clone [URL-DEL-REPOSITORIO]
cd eva03
```

2. **Configurar la base de datos**

```sql
CREATE DATABASE blog_interno;
CREATE USER 'blog_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON blog_interno.* TO 'blog_user'@'localhost';
```

3. **Configurar application.properties**

```properties
# Base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/blog_interno
spring.datasource.username=blog_user
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Thymeleaf
spring.thymeleaf.cache=false

# Configuración de errores
server.error.whitelabel.enabled=false
spring.web.resources.add-mappings=true
```

4. **Ejecutar la aplicación**

```bash
mvn spring-boot:run
```

5. **Acceder al sistema**

```
http://localhost:8080
```

## 📊 Cumplimiento de Requisitos

### Requisitos Funcionales (RF7.1 - RF7.10)

- ✅ **RF7.1** - Registro de autores con validación
- ✅ **RF7.2** - Listado de autores con filtros
- ✅ **RF7.3** - Edición de datos de autores
- ✅ **RF7.4** - Control de estados de autores
- ✅ **RF7.5** - Registro de publicaciones
- ✅ **RF7.6** - Listado de publicaciones
- ✅ **RF7.7** - Edición de publicaciones
- ✅ **RF7.8** - Control de estados de publicaciones
- ✅ **RF7.9** - Relación autor-publicación
- ✅ **RF7.10** - Vista detallada de publicaciones

### Requisitos No Funcionales (RNF7.V1 - RNF7.V4)

- ✅ **RNF7.V1** - Validación de longitud de títulos (5-150 caracteres)
- ✅ **RNF7.V2** - Validación de formato de correo electrónico
- ✅ **RNF7.V3** - Validación obligatoria de autor en publicaciones
- ✅ **RNF7.V4** - Estados de publicación (Borrador, Publicado, Archivado)

## 🎨 Capturas de Pantalla

### Panel Principal

- Dashboard con estadísticas en tiempo real
- Acceso rápido a funcionalidades principales
- Navegación intuitiva

### Gestión de Autores

- Lista con filtros y búsqueda
- Formularios de registro/edición
- Validación en tiempo real

### Gestión de Publicaciones

- Interface estilo blog moderno
- Editor con contador de caracteres
- Estados visuales diferenciados

## 🧪 Validaciones Implementadas

### Validación Frontend (JavaScript)

```javascript
// Validación de título en tiempo real
titulo.addEventListener('input', function() {
    const length = this.value.length;
    if (length < 5 || length > 150) {
        this.classList.add('is-invalid');
    } else {
        this.classList.add('is-valid');
    }
});

// Validación de email con RegEx
const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
if (!emailPattern.test(email.value)) {
    // Mostrar error
}
```

### Validación Backend (Jakarta)

```java
@Entity
public class Autores {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100)
    private String nombre;
  
    @Email(message = "Debe ser un email válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;
}
```

## 🔧 Manejo de Errores

### Páginas de Error Personalizadas

- **404.html** - Página no encontrada
- **500.html** - Error interno del servidor
- **error.html** - Error genérico

### Controlador de Errores

```java
@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        // Lógica de manejo de errores
        return "error/" + statusCode;
    }
}
```

## 🚀 Funcionalidades Destacadas

### Validación en Tiempo Real

- Contadores de caracteres dinámicos
- Retroalimentación visual inmediata
- Prevención de envíos inválidos

### Interface Moderna

- Diseño responsive
- Animaciones CSS
- Componentes Bootstrap personalizados

### Gestión de Estados

- Flujo de trabajo editorial
- Estados visuales diferenciados
- Transiciones controladas

## 📈 Métricas del Sistema

El dashboard principal muestra:

- Total de autores registrados
- Total de publicaciones
- Publicaciones por estado
- Autores activos/inactivos

## 🔮 Futuras Mejoras

- [ ] Sistema de autenticación y autorización
- [ ] Comentarios en publicaciones
- [ ] Categorías y etiquetas
- [ ] Búsqueda avanzada
- [ ] Exportación de contenido
- [ ] API REST
- [ ] Notificaciones push
- [ ] Editor WYSIWYG

## 👥 Contribuciones

Este proyecto fue desarrollado como parte de la evaluación académica del curso Desarrollo de Servicios Web 1.

## 📄 Licencia

Proyecto académico - Uso educativo únicamente.

---

**Desarrollado con ❤️ para IDAT - 2024**
