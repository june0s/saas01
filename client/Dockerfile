FROM maven:3.8.5-jdk-11 as build
WORKDIR /build

COPY src /build/src
COPY pom.xml /build/pom.xml

RUN mvn clean install

FROM adoptopenjdk/openjdk11
WORKDIR /app

COPY --from=build /build/target/client-1.0-SNAPSHOT.jar /app/client.jar
ENV DSERVER_IP "172.17.0.2"

ENTRYPOINT ["java", "-jar", "client.jar"]
