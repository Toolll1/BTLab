FROM amazoncorretto:11-alpine-jdk
COPY target/*.jar BTLab-service.jar
ENTRYPOINT ["java","-jar","/BTLab-service.jar"]