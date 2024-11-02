FROM openjdk:17-jdk
COPY build/libs/unid-0.0.1-SNAPSHOT.jar unid.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/unid.jar"]