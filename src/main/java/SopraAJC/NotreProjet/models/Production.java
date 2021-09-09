package SopraAJC.NotreProjet.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Production extends Batiment {

	@ManyToOne
	private Ressource ressource;
	private int quantiteProduite;
	
	public Production() {
	}

	public Production(String nom, double pointsDefense, int level, boolean ameliorable, int quantiteProduite) {
		super(nom, pointsDefense, level, ameliorable);
		this.quantiteProduite = quantiteProduite;
	}
	
	

	public Production(String nom, double pointsDefense, int level, boolean ameliorable, Ressource ressource, int quantiteProduite) {
		super(nom, pointsDefense, level, ameliorable);
		this.ressource = ressource;
		this.quantiteProduite = quantiteProduite;
	}

	public Ressource getRessource() {
		return ressource;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}

	public int getQuantiteProduite() {
		return quantiteProduite;
	}

	public void setQuantiteProduite(int quantiteProduite) {
		this.quantiteProduite = quantiteProduite;
	}
	
}
