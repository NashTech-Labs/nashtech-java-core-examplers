#
# Build stage
#

FROM maven:3.6.3-jdk-ea-1-slim AS build

WORKDIR usr/src/app

COPY . ./

RUN mvn clean package

#
# Package stage
#

FROM openjdk:20-ea-1-slim

ARG JAR_NAME="spring-boot-resilience"

WORKDIR /usr/src/app

COPY --from=build /usr/src/app/target/${JAR_NAME}.jar ./app.jar

CMD ["java","-jar", "./app.jar"]