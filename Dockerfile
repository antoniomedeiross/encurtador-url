# Usa uma imagem base leve com Java
FROM eclipse-temurin:21-jdk-alpine

# Define a pasta de trabalho dentro do container
WORKDIR /app

# Copia o arquivo .jar para dentro do container
COPY target/encurtador-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta que o Spring Boot usa
EXPOSE 8080

# Comando que roda o app
CMD ["java", "-jar", "app.jar"]
