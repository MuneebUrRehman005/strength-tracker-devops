pipeline {
    agent any

    stages {
        stage('Build with Maven') {
            steps {
                // Using ${WORKSPACE} ensures Jenkins passes the exact absolute path to Docker
                sh 'docker run --rm -v "${WORKSPACE}:/app" -w /app maven:3.9-eclipse-temurin-17 mvn clean package -DskipTests'
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