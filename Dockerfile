
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/auth-form-0.0.1-SNAPSHOT.jar /app/auth-form.jar
EXPOSE 8080
CMD ["java", "-jar", "mon-projet-spring-boot.jar"]
