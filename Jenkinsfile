pipeline {
    agent {
        docker {
            image 'maven:3.9-eclipse-temurin-17'
            args '-u root'
        }
    }

    stages {
        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            agent {
                node('builtin') 
            }
            steps {
                script {
                    sh 'docker build -t strength-tracker:latest .'
                }
            }
        }
    }
}