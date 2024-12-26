FROM openjdk:11
COPY target/procv*.jar /usr/src/procv.jar
COPY src/main/resources/application.properties /opt/conf/application.properties
CMD ["java", "-jar", "/usr/src/procv.jar", "--spring.config.location=file:/opt/conf/application.properties"]
