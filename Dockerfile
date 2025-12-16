# Stage 1: Build the app using Maven
FROM maven:3.9.3-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the Spring Boot application (skip tests for faster build)
RUN mvn clean package -DskipTests

# Stage 2: Run the app using a smaller Java runtime
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose port
ENV PORT 10000
EXPOSE $PORT

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
