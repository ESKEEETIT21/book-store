# Use an official Gradle image to build the Spring Boot app
FROM gradle:8.1.1-jdk17 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy only the gradle build files first to leverage Docker layer caching
COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# Download dependencies before copying the entire project
RUN gradle build --no-daemon || return 0

# Copy the rest of the application code
COPY . .

# Build the Spring Boot application
RUN gradle build --no-daemon

# Use an official OpenJDK runtime as a base image for running the application
FROM openjdk:17-jdk-slim


# Set the working directory inside the container
WORKDIR /app

RUN apt-get update && apt-get install -y curl
# Copy the built application from the previous stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the port Spring Boot is running on
EXPOSE 8080

# Set the entry point for the container to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]