FROM openjdk:17-jdk-slim
COPY ./ /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/build/libs/<DEIN_JAR_NAME>.jar"]