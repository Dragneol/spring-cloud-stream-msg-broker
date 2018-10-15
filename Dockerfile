FROM java:8 as build

WORKDIR /app

COPY . .

RUN chmod +x ./gradlew

RUN ./gradlew build

FROM openjdk:8-jre-alpine

WORKDIR /opt/

COPY --from=build /app/app/build/libs/app-0.0.1-SNAPSHOT.jar /opt/app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/opt/app.jar"]