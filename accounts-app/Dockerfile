FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
#EXPOSE ${ACCOUNTS_SERVER_PORT}
ENTRYPOINT ["java", "-jar", "app.jar"]