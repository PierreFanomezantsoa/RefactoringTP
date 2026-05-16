# Utilise l'image officielle Eclipse Temurin (Java 17) sur une base Linux Alpine très légère
FROM eclipse-temurin:17-jdk-alpine

# Définit le dossier de travail dans le conteneur
WORKDIR /app

# Copie le fichier .jar généré par l'étape Maven précédente dans le conteneur
COPY target/*.jar app.jar

# Commande pour exécuter l'application Java
ENTRYPOINT ["java", "-jar", "app.jar"]