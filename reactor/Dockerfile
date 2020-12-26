FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/reactor-1.0-SNAPSHOT.jar
WORKDIR /opt/app
ADD ${JAR_FILE} rx.jar
ENTRYPOINT ["java","-jar","rx.jar"]
EXPOSE 8080
