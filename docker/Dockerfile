FROM openjdk:8u151-alpine3.7

COPY ./resources /tmp/resources
COPY ./demo-0.0.1-SNAPSHOT.jar /tmp/app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dloader.path=/tmp/resources","-jar","/tmp/app.jar"]
