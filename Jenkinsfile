pipeline{
    agent any
    tools {
        maven 'maven_3_8_5'
    }
    stages {
        stage('Build Maven') {
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sjam11/recruitment-jenkins.git']]])

                bat 'mvn clean install'
                
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                  bat 'docker build -t sjam16/my-app-1.0 .'
                }
            }
        }
        stage('Deploy Docker Image') {
            steps {
                script {
                 withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                    bat 'docker login -u sjam16 -p ${dockerhubpwd}'
                 }  
                 bat 'docker push sjam16/my-app-1.0'
                }
            }
        }
    }
}