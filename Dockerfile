FROM openjdk:17-jdk-slim

EXPOSE 8082

ARG JAR_FILE=target/appointment-service.jar
ADD ${JAR_FILE} appointment-service.jar

ENTRYPOINT exec java -jar /appointment-service.jar