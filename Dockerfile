FROM eclipse-temurin:17-ubi9-minimal

RUN ./gradlew build

COPY build/libs/javadoc-api-1.0-SNAPSHOT-all.jar /javadoc-api-1.0-SNAPSHOT-all.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/javadoc-api-1.0-SNAPSHOT-all.jar"]