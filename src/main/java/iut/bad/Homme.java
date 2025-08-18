package iut.bad;

public class Homme extends Humain {
	
	
	public Homme(String nom, String prenom ,int age) {
		super(nom, prenom, age);
	}
	@Override
	public String toString() {
		return "Monsieur :" + nom + " , prénom : "+ prenom +" et " + age + " ans";
	}
	
}
