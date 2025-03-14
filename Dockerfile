FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/user_profile_service-0.0.1-SNAPSHOT.jar app.jar
COPY .env .
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]