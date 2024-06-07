# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the executable JAR file from the target directory to the container
COPY target/my-spring-boot-app.jar app.jar
# If you're using Gradle, use: COPY build/libs/my-spring-boot-app.jar app.jar

# Expose the port that your Spring Boot app runs on
EXPOSE 8080

# Set the default command to run your Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
