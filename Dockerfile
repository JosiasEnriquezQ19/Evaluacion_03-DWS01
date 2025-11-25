# Dockerfile para EVA03 - Spring Boot App
FROM eclipse-temurin:21-jdk-alpine

# Información del mantenedor
LABEL maintainer="eva03-app"

# Establecer directorio de trabajo
WORKDIR /app

# Copiar archivos de Maven
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Dar permisos de ejecución al wrapper de Maven
RUN chmod +x mvnw

# Descargar dependencias (capa de cache)
RUN ./mvnw dependency:go-offline -B

# Copiar código fuente
COPY src ./src

# Construir la aplicación
RUN ./mvnw clean package -DskipTests

# Exponer puerto (Render asigna automáticamente)
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "-Dserver.port=${PORT:-8080}", "target/eva03-0.0.1-SNAPSHOT.jar"]