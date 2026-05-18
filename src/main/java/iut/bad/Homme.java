package iut.bad;

public class Homme extends Humain {
	//commentaire : la classe Homme hérite de la classe Humain, elle représente un homme avec les mêmes attributs et méthodes que Humain, mais avec une méthode toString différente pour afficher "Monsieur" au lieu de "Madame"
	
	public Homme(String nom, String prenom ,int age) {
		super(nom, prenom, age);
	}
	@Override
	public String toString() {
		return "Monsieur :" + nom + " , prénom : "+ prenom +" et " + age + " ans";
	}
	
}
