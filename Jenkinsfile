pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "maroi/marwadevops:latest"
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build Maven') {
            steps {
                bat 'mvnw.cmd clean package -DskipTests'
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                script {
                    // Utilise uniquement WSL pour Docker → plus stable sur Windows avec Docker Desktop
                    bat "wsl docker build -t ${DOCKER_IMAGE} ."

                    withCredentials([usernamePassword(credentialsId: 'dockerhub-creds',
                                                     usernameVariable: 'DOCKER_USER',
                                                     passwordVariable: 'DOCKER_PASS')]) {
                        bat """
                            wsl docker login -u %DOCKER_USER% -p %DOCKER_PASS%
                        """
                    }

                    bat "wsl docker push ${DOCKER_IMAGE}"
                }
            }
        }

        stage('Deploy to Cluster') {
            steps {
                // Si tu utilises Kubernetes dans Docker Desktop
                bat 'wsl kubectl apply -f k8s/deployment.yaml'
            }
        }
    }

    post {
        success {
            echo "Pipeline exécuté avec succès ! 🎉"
        }
        failure {
            echo "Pipeline échoué ! 😞"
        }
    }
}