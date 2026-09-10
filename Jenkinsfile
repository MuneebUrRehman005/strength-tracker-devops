pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/MuneebUrRehman005/FirstProject.git'
            }
        }

        stage('Build with Maven') {
            steps {
                // This replaces the old raw javac compile with Maven
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Builds the Docker image using your Dockerfile
                    sh 'docker build -t strength-tracker:latest .'
                }
            }
        }
    }
}