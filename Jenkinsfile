pipeline{
    agent any
    tools {
        maven 'maven_3_8_5'
    }
    stages {
        stage('Build Maven') {
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sjam11/recruitment-jenkins.git']]])

                sh 'mvn clean install'
                
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                  sh 'sudo docker build -t sjam16/my-app-1.0 .'
                }
            }
        }
        stage('Deploy Docker Image') {
            steps {
                script {  
                 sh 'sudo docker push sjam16/my-app-1.0'
                }
            }
        }
    }
}