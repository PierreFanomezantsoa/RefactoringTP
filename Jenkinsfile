pipeline {
    agent any

    environment {
        DOCKERHUB_USER = 'pierre05'
        IMAGE_NAME     = 'refactoring-tp'
        // Gardé en variable mais on va aussi le mettre en dur plus bas pour éliminer tout bug
        NOTIFICATION_EMAIL = 'rnandrasanarivopierre@gmail.com' 
    }

    tools {
        // Remplace bien par le nom exact configuré dans Administrer Jenkins > Tools (ex: '2Maven')
        maven 'Maven' 
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

    // 📩 SECTION POST-ACTIONS SÉCURISÉE
    post {
        // 1. S'exécute uniquement si le build plante
        failure {
            echo '❌ Le Build a échoué ! Envoi de la notification par email...'
            emailext body: "Le Build #${env.BUILD_NUMBER} a échoué. Veuillez vérifier la console Jenkins pour corriger les erreurs.",
                     subject: "Jenkins: Échec du Build #${env.BUILD_NUMBER} - ${env.JOB_NAME}", 
                     to: 'rnandrasanarivopierre@gmail.com',
                     attachLog: true
        }
        
        // 2. Décommente ou utilise ce bloc pour FORCER un test de mail immédiat à chaque build
        always {
            echo '🔄 Post-action Always : Envoi d\'un e-mail de suivi de statut...'
            emailext body: "Notification de statut pour le Build #${env.BUILD_NUMBER}. Statut actuel : ${currentBuild.currentResult}",
                     subject: "Jenkins Suivi: Build #${env.BUILD_NUMBER} - ${env.JOB_NAME}", 
                     to: 'rnandrasanarivopierre@gmail.com'
        }
    }
}