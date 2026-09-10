pipeline {
    agent any

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t strength-tracker:latest .'
                }
            }
        }
        stage('SonarQube Analysis') {
            steps {
                script {
                    // Compile java files locally in the workspace for the scanner
                    sh 'javac Exercise.java StrengthTrackerApp.java'
                    
                    def scannerHome = tool 'SonarScanner'
                    withSonarQubeEnv() {
                        sh "${scannerHome}/bin/sonar-scanner"
                    }
                }
            }
        }
        stage('Push to Registry') {
            steps {
                echo 'Pushing the compiled image to Docker Hub...'
            }
        }
    }
}