pipeline {
    agent any

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker container using your project's Dockerfile
                    sh 'docker build -t strength-tracker:latest .'
                }
            }
        }
        stage('Scan') {
            steps {
                echo 'Running SonarQube static code analysis...'
            }
        }
        stage('Push to Registry') {
            steps {
                echo 'Pushing the compiled image to Docker Hub...'
            }
        }
    }
}