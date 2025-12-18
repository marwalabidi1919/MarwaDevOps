pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "maroi/marwadevops:latest" // Image Docker complète avec ton Docker Hub
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/marwalabidi1919/MarwaDevOps.git'
            }
        }

        stage('Build Maven') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                script {
                    // Build Docker image
                    sh "docker build -t ${DOCKER_IMAGE} ."

                    // Login Docker (assurez-vous que vos identifiants sont configurés dans Jenkins Credentials)
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh "echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin"
                    }

                    // Push Docker image
                    sh "docker push ${DOCKER_IMAGE}"
                }
            }
        }

        stage('Deploy to Cluster') {
            steps {
                // Exemple : déploiement sur Kubernetes
                sh "kubectl apply -f k8s/deployment.yaml"
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
