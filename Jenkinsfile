pipeline {
    agent any

    environment {
        // ⚠️ REMPLACE par ton véritable identifiant Docker Hub récupéré sur ton profil
        DOCKERHUB_USER = 'pierre05'
        IMAGE_NAME     = 'refactoring-tp'
    }

    tools {
        // Vérifie bien que ce nom correspond exactement à celui dans Administrer Jenkins > Tools
        maven 'Maven' 
    }

    stages {
        stage('Git Checkout') {
            steps {
                echo '📥 Récupération du code source depuis GitHub...'
                git branch: 'main', 
                    credentialsId: '0701Me12', 
                    url: 'https://github.com/PierreFanomezantsoa/RefactoringTP'
            }
        }

        stage('Maven Build') {
            steps {
                echo '🏗️ Compilation du projet et génération du fichier JAR...'
                // Génère le package JAR dans le dossier target/ sans lancer les tests
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('UnitTestExecution') {
            steps {
                echo '🧪 Exécution des tests unitaires...'
                // Lance les tests JUnit de ton application
                bat 'mvn test'
            }
        }

        stage('Build the docker image') {
            steps {
                echo "🐳 Construction de l'image Docker (Build #${env.BUILD_NUMBER})..."
                // Construit l'image locale en utilisant l'image de base eclipse-temurin:17-jdk-alpine configurée dans ton Dockerfile
                bat "docker build -t ${env.DOCKERHUB_USER}/${IMAGE_NAME}:${env.BUILD_NUMBER} ."
            }
        }

        stage('Push to Docker Hub') {
            steps {
                echo '📤 Connexion à Docker Hub et publication de l\'image...'
                // Récupération sécurisée du Personal Access Token Docker Hub via l'ID que tu as créé dans Jenkins
                withCredentials([string(credentialsId: 'dockerhubpass', variable: 'dockerHubPass')]) {
                    // Authentification et push de l'image sur ton dépôt distant
                    bat "docker login -u ${env.DOCKERHUB_USER} -p %dockerHubPass%"
                    bat "docker push ${env.DOCKERHUB_USER}/${IMAGE_NAME}:${env.BUILD_NUMBER}"
                }
            }
        }
    }
}