FROM maven:3.9.15-amazoncorretto-21 as build


WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/target/analise-tempo.jar app.jar

COPY data.json .

ENTRYPOINT ["java", "-jar", "app.jar"]
