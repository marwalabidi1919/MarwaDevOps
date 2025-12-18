pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "maroi/marwadevops:latest"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/marwalabidi1919/MarwaDevOps.git'
            }
        }

        stage('Build Maven') {
            steps {
                // Exécution Maven via WSL
                bat 'wsl mvn clean install'
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                script {
                    // Build Docker image via WSL
                    bat "wsl docker build -t ${DOCKER_IMAGE} ."

                    // Login Docker Hub via credentials Jenkins
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        bat 'wsl bash -c "echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin"'
                    }

                    // Push Docker image
                    bat "wsl docker push ${DOCKER_IMAGE}"
                }
            }
        }

        stage('Deploy to Cluster') {
            steps {
                // Déploiement Kubernetes via WSL
                bat 'wsl kubectl apply -f k8s/deployment.yaml'
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
