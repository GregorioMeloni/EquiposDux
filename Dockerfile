# Start with a base image containing Java runtime (openjdk is an example)
FROM openjdk:17

# Add Maintainer Info
LABEL maintainer="your_email@example.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file (adjust the name as necessary)
ARG JAR_FILE=prueba-tecnica-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD target/${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
