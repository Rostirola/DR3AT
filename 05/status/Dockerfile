FROM amazoncorretto:21
LABEL authors="juanrostirola"
WORKDIR /app
COPY build/libs/status-0.0.1-SNAPSHOT.jar status.jar
ENTRYPOINT  ["java", "-jar", "status.jar"]