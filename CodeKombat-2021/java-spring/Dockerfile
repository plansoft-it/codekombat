#la versione java è la 11, modifica il FROM

FROM openjdk:11
#mvn dalla 8 in poi

RUN mkdir /app

COPY /src/ /app/src/
#copia le dir del progetto dentro il contesto di build src e .mvn assieme al file pom

COPY .mvn/ /app/.mvn/

COPY mvnw /app/mvnw

COPY pom.xml /app/

WORKDIR /app

RUN ./mvnw clean package

CMD java -jar target/codekombat-0.0.1-SNAPSHOT.jar
#lancia il programma java impacchettato specificando l'entrypoint dell'applicazione
