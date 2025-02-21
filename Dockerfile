FROM maven:3.9.2-eclipse-temurin-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:11
COPY --from=build /app/target/procv*.jar /usr/src/procv.jar
docker run -v /src/main/resources/application.properties:/opt/conf/application.properties procv/
CMD ["java", "-jar", "/usr/src/procv.jar", "--spring.config.location=file:/opt/conf/application.properties"]
