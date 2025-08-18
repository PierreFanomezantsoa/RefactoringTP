package iut.bad;

public class Humain implements consommation {
	protected String nom;
	protected String prenom;
	protected int age;
	public Humain(String nom, String prenom ,int age) {
		super();
		this.nom= nom;
		this.prenom= prenom;
		this.age=age;
	}
	public void details() {
		System.out.println(toString() );
	}
	@Override
	public String toString() {
		return "votre nom :" + nom + " , prénom : "+ prenom +" et " + age + " ans";
	}
	@Override
	public void manger() {
		System.out.println("Madame ou Monsieur "+ nom+ " vous êtes mangé" );
	}
	@Override
	public void boire() {
		System.out.println("Madame ou Monsieur "+ nom+ " vous êtes mangé" );
	}

}