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
                        
                        // Tag and push both latest and the specific build number
                        sh 'docker tag strength-tracker:latest mur123muneeb/strength-tracker:latest'
                        sh "docker tag strength-tracker:latest mur123muneeb/strength-tracker:build-${BUILD_NUMBER}"
                        
                        sh 'docker push mur123muneeb/strength-tracker:latest'
                        sh "docker push mur123muneeb/strength-tracker:build-${BUILD_NUMBER}"
                    }
                }
            }
        }
        stage('Update Config Repo') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'github-credentials-id', 
                                                       usernameVariable: 'GIT_USER', 
                                                       passwordVariable: 'GIT_PASS')]) {
                        sh """
                            git config user.name "Jenkins CI"
                            git config user.email "jenkins@build.local"
                            
                            # Dynamically update the tag in values.yaml using proper shell expansion
                            sed -i "s/tag:.*/tag: \\"build-${BUILD_NUMBER}\\"/" charts/strength-tracker/values.yaml
                            
                            git add charts/strength-tracker/values.yaml
                            git commit -m "Update image tag to build-${BUILD_NUMBER}" || echo "No changes to commit"
                            
                            # Push using the authenticated remote URL
                            git remote set-url origin https://${GIT_USER}:${GIT_PASS}@github.com/MuneebUrRehman005/strength-tracker-devops.git
                            git push origin HEAD:main
                        """
                    }
                }
            }
        }
    }
}