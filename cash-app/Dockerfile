FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
#EXPOSE ${CASH_APP_SERVER_PORT}
ENTRYPOINT ["java", "-jar", "app.jar"]