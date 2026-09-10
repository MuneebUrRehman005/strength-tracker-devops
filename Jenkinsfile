pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Code successfully checked out by Jenkins SCM.'
            }
        }

        stage('Build with Maven') {
            steps {
                // Runs Maven using a Docker container, pointing to the workspace files
                sh 'docker run --rm -v ${WORKSPACE}:/app -w /app maven:3.9-eclipse-temurin-17 mvn clean package -DskipTests'
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