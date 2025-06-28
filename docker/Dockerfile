FROM ubuntu:latest
LABEL authors="csh09"

ENV SPRING_PROFILES_ACTIVE=prod

FROM openjdk:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]