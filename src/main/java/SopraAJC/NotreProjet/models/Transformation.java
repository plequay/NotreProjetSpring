package SopraAJC.NotreProjet.models;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Transformation  extends Batiment{

	public Transformation() {
		super();
		this.setAmeliorable(false);
	}
	
	public Transformation(String nom, double def, int level,boolean ameliorable)
	{
		super(nom,  def, level, ameliorable);
	}	
	
	public Transformation(Integer id, String nom, double def, int level,boolean ameliorable)
	{
		super(id, nom,  def, level, ameliorable);
	}
	
	public Transformation(String nom, double def, int level,boolean ameliorable, List<CoutBatiment> cost)
	{
		super(nom,  def, level, ameliorable, cost);
	}

}
