pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "maroi/marwadevops:latest"
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm  // Plus propre, pas besoin de re-checkout manuel
            }
        }

        stage('Build Maven') {
            steps {
                bat 'mvnw.cmd clean install'
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                script {
                    // Essaie d'abord Docker natif Windows, sinon fallback WSL
                    bat """
                        docker build -t ${DOCKER_IMAGE} . || wsl docker build -t ${DOCKER_IMAGE} .
                    """

                    withCredentials([usernamePassword(credentialsId: 'dockerhub-creds',
                                                     usernameVariable: 'DOCKER_USER',
                                                     passwordVariable: 'DOCKER_PASS')]) {
                        bat """
                            (echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin) || ^
                            wsl bash -c "echo '%DOCKER_PASS%' | docker login -u '%DOCKER_USER%' --password-stdin"
                        """
                    }

                    bat """
                        docker push ${DOCKER_IMAGE} || wsl docker push ${DOCKER_IMAGE}
                    """
                }
            }
        }

        stage('Deploy to Cluster') {
            steps {
                bat 'kubectl apply -f k8s/deployment.yaml'
            }
        }
    }

    post {
        success {
            echo "Pipeline exécuté avec succès !"
        }
        failure {
            echo "Pipeline échoué !"
        }
    }
}