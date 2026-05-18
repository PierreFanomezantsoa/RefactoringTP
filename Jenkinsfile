pipeline {
    agent any

    environment {
        DOCKERHUB_USER = 'pierre05'
        IMAGE_NAME     = 'refactoring-tp'
        // Définis ton adresse email ici pour centraliser la configuration
        NOTIFICATION_EMAIL = 'rnandrasanarivopierre@gmail.com' 
    }

    tools {
        maven '2Maven' 
    }

    stages {
        stage('Git Checkout') {
            steps {
                echo '📥 Récupération du code source depuis GitHub...'
                git branch: 'main', 
                    credentialsId: '0701Me12', 
                    url: 'https://github.com/PierreFanomezantsoa/RefactoringTP.git'
            }
        }

        stage('Maven Build') {
            steps {
                echo '🏗️ Compilation du projet et génération du fichier JAR...'
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('UnitTestExecution') {
            steps {
                echo '🧪 Exécution des tests unitaires...'
                bat 'mvn test'
            }
        }

        stage('Build the docker image') {
            steps {
                echo "🐳 Construction de l'image Docker (Build #${env.BUILD_NUMBER})..."
                bat "docker build -t ${env.DOCKERHUB_USER}/${IMAGE_NAME}:${env.BUILD_NUMBER} ."
            }
        }

        stage('Push to Docker Hub') {
            steps {
                echo '📤 Connexion à Docker Hub et publication de l\'image...'
                withCredentials([string(credentialsId: 'dockerhubpass', variable: 'dockerHubPass')]) {
                    bat "docker login -u ${env.DOCKERHUB_USER} -p %dockerHubPass%"
                    bat "docker push ${env.DOCKERHUB_USER}/${IMAGE_NAME}:${env.BUILD_NUMBER}"
                }
            }
        }
    }

    // 📩 SECTION POST-ACTIONS (À ajouter ici)
    post {
        failure {
            echo '❌ Le Build a échoué ! Envoi de la notification par email...'
            emailext body: "Le Build #${env.BUILD_NUMBER} a échoué. Vérifiez la console Jenkins pour plus de détails.",
                     recipientProviders: [requestor()], 
                     subject: "Jenkins: Échec du Build #${env.BUILD_NUMBER} - ${env.JOB_NAME}", 
                     to: "${env.NOTIFICATION_EMAIL}"
        }
    }
}