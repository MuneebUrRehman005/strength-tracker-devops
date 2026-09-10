pipeline {
    agent any

    stages {
        stage('Debug Workspace') {
            steps {
                sh 'pwd'
                sh 'ls -la'
            }
        }

        stage('Build with Maven') {
            steps {
                // If Maven isn't installed locally on the Jenkins node, let's see if we can use a direct build or check path
                sh 'mvn clean package -DskipTests'
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