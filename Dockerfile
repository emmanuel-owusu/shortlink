# Stage 1: Build the application (Builder)
FROM openjdk:17-alpine AS builder

RUN apk add --no-cache gradle curl

WORKDIR /app

COPY . .

# download the Gradle distribution
RUN curl https://services.gradle.org/distributions/gradle-8.7-bin.zip -O gradle.zip && unzip gradle.zip && chmod +x gradlew/bin/gradlew

RUN ./gradlew install

# Stage 2: Run the application (Runner)
FROM openjdk:17-slim

# Copy the built JAR file from the builder stage
COPY --from=builder /app/build/libs/*.jar /app/lib/


WORKDIR /app

# Expose the port used by the Spring Boot application
EXPOSE 8080

# Run the application using the built JAR
CMD ["java", "-jar", "/app/lib/*.jar"]
