# Stage 1: Build source code dengan Gradle
FROM gradle:8.5-jdk17 AS build
WORKDIR /app
COPY . .
# Menjalankan build gradle dan skip unit test untuk mempercepat proses
RUN ./gradlew bootJar -x test

# Stage 2: Jalankan file .jar hasil build Gradle
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Jalur folder hasil build Gradle berada di build/libs/
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]