FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY src src

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# OpenTelemetry Java agent - copy the agent into the image (download `opentelemetry-javaagent.jar` into the build context)
COPY opentelemetry-javaagent.jar /otel/opentelemetry-javaagent.jar

EXPOSE 8083

# Minimal OTEL envs (can be overridden by k8s env or Docker run)
ENV OTEL_SERVICE_NAME=cart-service
ENV OTEL_TRACES_EXPORTER=otlp
ENV OTEL_EXPORTER_OTLP_ENDPOINT=http://jaeger:4318

# Start JVM with the javaagent
ENTRYPOINT ["java", "-javaagent:/otel/opentelemetry-javaagent.jar", "-jar", "app.jar"]