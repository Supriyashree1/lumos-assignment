# Use Java 17
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy all project files
COPY . .

# Make Maven wrapper executable
RUN chmod +x mvnw

# Build the application (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# Expose the port Render will use
EXPOSE 8080

# Use Render's dynamic port
ENV PORT 8080

# Run the jar (replace YOURJAR.jar with actual jar name if needed)
CMD ["sh", "-c", "java -Dserver.port=$PORT -jar target/*.jar"]
