pipeline{
    agent any
    
    stages {
        stage('Build Maven') {
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/sjam11/recruitment-jenkins.git']]])

                "mvn clean install"
                
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                'docker build -t sjam16/my-app-1.0 .'
                }
            }
        }
        stage('Deploy Docker Image') {
            steps {
                script {
                 withCredentials([string(credentialsId: 'jenkdockid', variable: 'jenkdockid')]) {
                    'docker login -u sjam16 -p ${jenkdockid}'
                 }  
                 'docker push sjam16/my-app-1.0'
                }
            }
        }
    }
}