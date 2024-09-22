FROM amazoncorretto:21
LABEL authors="juanrostirola"
WORKDIR /app
COPY build/libs/eureka-0.0.1-SNAPSHOT.jar eureka.jar
ENTRYPOINT  ["java", "-jar", "eureka.jar"]