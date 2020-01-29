FROM openjdk:14-jdk-alpine3.10

COPY target/*.jar /deployments/app.jar

USER 1001

ENTRYPOINT [ "/deployments/run-java.sh" ]

