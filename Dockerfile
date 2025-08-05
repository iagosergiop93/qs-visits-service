# Docker commands: 
# docker build -t qs-authserver .
# docker tag qs-authserver:latest $REPO_NAME:latest

# Use an official OpenJDK runtime as a parent image
FROM amazoncorretto:21-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the fat jar into the container at /app
COPY build/libs/*SNAPSHOT.jar app.jar

# Make port 8080 available to the world outside this container
EXPOSE 8084

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
