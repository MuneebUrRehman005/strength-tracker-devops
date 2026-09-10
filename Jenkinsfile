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
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', 
                                                     usernameVariable: 'DOCKER_USER', 
                                                     passwordVariable: 'DOCKER_PASS')]) {
                        sh "echo ${DOCKER_PASS} | docker login -u ${DOCKER_USER} --password-stdin"
                        sh 'docker tag strength-tracker:latest mur123muneeb/strength-tracker:latest'
                        sh 'docker push mur123muneeb/strength-tracker:latest'
                    }
                }
            }
        }
    }
}