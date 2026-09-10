pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Code successfully checked out.'
            }
        }

        stage('Build with Maven') {
            steps {
                // Run Maven using an official Maven Docker container so you don't need it pre-installed in Jenkins
                sh 'docker run --rm -v $PWD:/app -w /app maven:3.9-eclipse-temurin-17 mvn clean package -DskipTests'
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