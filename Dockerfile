FROM openjdk:8
EXPOSE 8080
ADD sjam16/jenkins-exjobb.jar jenkins-exjobb.jar
ENTRYPOINT ["java","-jar","/jenkins-exjobb.jar"]