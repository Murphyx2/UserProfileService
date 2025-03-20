FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/user_profile_service-0.0.1-SNAPSHOT.jar app.jar
COPY .env .
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]