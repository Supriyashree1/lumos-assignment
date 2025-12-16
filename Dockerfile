# Use Java 17
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy all project files
COPY . .

# Build the application using system Maven
RUN mvn clean package -DskipTests

# Expose port
EXPOSE 10000

# Run the JAR
CMD ["sh", "-c", "java -jar target/*.jar"]
