package iut.bad;

public class Humain {
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

}