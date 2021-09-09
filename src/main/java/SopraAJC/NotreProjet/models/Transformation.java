package SopraAJC.NotreProjet.models;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Transformation  extends Batiment{

	public Transformation() {
		super();
		this.setAmeliorable(false);
	}
	
	public Transformation(Integer id, String nom, int level, double def,double att,boolean ameliorable)
	{
		super(id, nom, level, def, att, ameliorable);
	}
	
	public Transformation(String nom, int level, double def,double att,boolean ameliorable)
	{
		super(nom, level, def, att, ameliorable);
	}
	
	public Transformation(String nom, double def, double att, int level, boolean ameliorable, List<CoutBatiment> cost) {
		super(nom, def, att, level, ameliorable, cost);
	}
}
