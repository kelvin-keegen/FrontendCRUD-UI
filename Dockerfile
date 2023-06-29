FROM amazoncorretto:17-alpine
EXPOSE 7171
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]