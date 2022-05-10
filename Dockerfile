FROM openjdk:16-jdk-alpine
COPY target/*.jar backend-user-service.jar
ENTRYPOINT ["java", "-jar", "/backend-user-service.jar"]