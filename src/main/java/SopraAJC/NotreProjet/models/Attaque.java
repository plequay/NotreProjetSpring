package SopraAJC.NotreProjet.models;

import javax.persistence.Entity;


@Entity
public class Attaque extends Batiment {
	
	private double pointsDAttaque;
	
	public Attaque() {
	}

	public Attaque(String nom, double pointsDefense, double pointsDAttaque, int level, boolean ameliorable) {
		super(nom, pointsDefense, level, ameliorable);
		this.pointsDAttaque = pointsDAttaque;
	}
	
	public double getPointsDAttaque() {
		return pointsDAttaque;
	}

	public void setPointsDAttaque(double pointsDAttaque) {
		this.pointsDAttaque = pointsDAttaque;
	}

}
