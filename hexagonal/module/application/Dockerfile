FROM openjdk:11.0.6-jre-buster
ARG JAR_FILE=target/application-*.jar
ADD $JAR_FILE app.jar
#ARG APP_NAME
#ENV APP_NAME=$APP_NAME
EXPOSE 8888
CMD ["java", "-jar", "app.jar"]