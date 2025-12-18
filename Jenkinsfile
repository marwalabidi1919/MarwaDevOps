pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "maroi/marwadevops:latest"
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Le checkout SCM est déjà fait automatiquement par Jenkins pour un Pipeline from SCM
                // Ce stage est redondant → tu peux le supprimer
                // Mais si tu veux le garder pour clarté, utilise checkout scm
                checkout scm
            }
        }

        stage('Build Maven') {
            steps {
                // Utilise le Maven Wrapper Windows (mvnw.cmd) → pas besoin de Maven installé ni de WSL
                bat 'mvnw.cmd clean install'
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                script {
                    // Build de l'image Docker (Docker Desktop doit être installé et en mode Linux containers)
                    bat "docker build -t ${DOCKER_IMAGE} ."

                    // Login Docker Hub avec credentials Jenkins
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-creds',
                                                     usernameVariable: 'DOCKER_USER',
                                                     passwordVariable: 'DOCKER_PASS')]) {
                        bat """
                            echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
                        """
                    }

                    // Push de l'image
                    bat "docker push ${DOCKER_IMAGE}"
                }
            }
        }

        stage('Deploy to Cluster') {
            steps {
                // Suppose que kubectl est installé sur Windows et accessible dans le PATH
                // Ou que tu utilises Docker Desktop avec Kubernetes activé
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