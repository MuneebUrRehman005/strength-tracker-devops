pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                // Declarative pipeline automatically checks out the repo, 
                // so we just echo a success message here.
                echo 'Code successfully checked out by Jenkins SCM.'
            }
        }

        stage('Build with Maven') {
            steps {
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