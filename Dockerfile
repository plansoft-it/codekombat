FROM openjdk:8-jdk-alpine

EXPOSE 8080

COPY *.jar app.jar

CMD sleep 10 && java -jar app.jar
