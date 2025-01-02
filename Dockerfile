FROM eclipse-temurin:21-ubi9-minimal

# Copy the project files into the container
COPY . /app

# Set the working directory
WORKDIR /app

# Run the build process
RUN ./gradlew build

# Copy the resulting JAR file
COPY build/libs/javadoc-api-1.0-SNAPSHOT-all.jar /javadoc-api-1.0-SNAPSHOT-all.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/javadoc-api-1.0-SNAPSHOT-all.jar"]