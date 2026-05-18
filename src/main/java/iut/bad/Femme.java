package iut.bad;
// Classe Femme, qui hérite de la classe Humain et représente une femme
public class Femme extends Humain{
	
	public Femme(String nom, String prenom, int age) {
		super(nom, prenom, age);
	}
	@Override
	public String toString() {
		return "Madame :" + nom + " , prénom : "+ prenom +" et " + age + " ans";
	}
	public static void main(String[] args) {
		Homme h= new Homme("Zandry", "miminirina",14);
		Femme f= new Femme("Harisoa", "Fifa", 12);
		h.ami(f);
		f.ami(h,500);
	}
}