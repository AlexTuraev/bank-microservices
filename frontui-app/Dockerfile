FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
#EXPOSE ${FRONTUI_SERVER_PORT}
ENTRYPOINT ["java", "-jar", "app.jar"]