# Use the official Gradle image to build the application
FROM gradle:8.10.2-jdk17 AS build

# Set the working directory inside the container
WORKDIR /airbnb

# Copy the Gradle build files
COPY build.gradle settings.gradle ./
COPY gradle ./gradle

# Copy the source code
COPY src ./src

# Build the application (this will generate the JAR file)
RUN gradle build -x test

# Use OpenJDK to run the application
FROM openjdk:17-jdk-slim

# Set the working directory for the runtime image
WORKDIR /airbnb

# Copy the JAR file from the build stage to the runtime image
COPY --from=build /airbnb/build/libs/*.jar airbnb-0.0.1-SNAPSHOT.jar

# Expose the port your application runs on (default is 8080 for Spring Boot)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/airbnb/airbnb-0.0.1-SNAPSHOT.jar"]
