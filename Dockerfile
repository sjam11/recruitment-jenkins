FROM openjdk:8
EXPOSE 8080
ADD /jenkins-exjobb.jar jenkins-exjobb.jar
ENTRYPOINT ["java","-jar","/jenkins-exjobb.jar"]