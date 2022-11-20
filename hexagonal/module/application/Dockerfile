FROM openjdk:11.0.6-jre-buster
ARG JAR_FILE=target/application-*.jar
ADD $JAR_FILE app.jar
ARG OS_LOGIN
ENV OS_LOGIN=$OS_LOGIN
EXPOSE 8888
CMD ["java", "-jar", "app.jar"]