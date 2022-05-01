FROM openjdk:8
EXPOSE 8080
ADD target/jenkins-exjobb.jar jenkinsecjobb.jar
ENTRYPOINT ["java","-jar","/jenkinsexjobb.jar"]