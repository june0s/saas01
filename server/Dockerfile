FROM maven:3.8.5-jdk-11 as build
WORKDIR /build

COPY src /build/src
COPY pom.xml /build/pom.xml

RUN mvn clean install

# server-1.0-SNAPSHOT.jar

FROM adoptopenjdk/openjdk11
WORKDIR /app

COPY --from=build /build/target/server-1.0-SNAPSHOT.jar /app/server.jar
#COPY conf/log4j.properties /app/server/conf/

ENTRYPOINT ["java", "-jar", "server.jar"]
