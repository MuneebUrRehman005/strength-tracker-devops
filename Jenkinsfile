pipeline {
    agent any

    stages {
        stage('Build with Maven') {
            steps {
                // Explicitly map the current workspace path to the Maven container
                sh 'docker run --rm -v /var/jenkins_home/workspace/strength-tracker-pipeline:/app -w /app maven:3.9-eclipse-temurin-17 mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t strength-tracker:latest .'
                }
            }
        }
    }
}