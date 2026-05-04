FROM maven:3.9.15-amazoncorretto-21 as build


WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app



COPY --from=build /app/target/analise-tempo.jar /app-exec.jar
COPY script_copy.sh /script_copy.sh
RUN sed -i 's/\r//' /script_copy.sh && chmod +x /script_copy.sh

ENTRYPOINT ["/script_copy.sh"]
