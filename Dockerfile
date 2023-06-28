FROM openjdk:19-jdk
VOLUME /tmp
COPY nashtech-resilience-exampler/target/nashtech-resilience-exampler-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]