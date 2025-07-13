# Etapa 1: build da aplicação
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app
COPY . .

RUN ./mvnw clean package

# Etapa 2: imagem final com o jar
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app
COPY --from=build /app/target/encurtador-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
