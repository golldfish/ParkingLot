FROM azul/zulu-openjdk-alpine:11-jre

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app/main.jar
ADD src/main/resources/application.properties app/application.properties
ENTRYPOINT ["java" ,"-Dspring.config.location=classpath:file:///app/application.properties","-jar","/app/main.jar"]

