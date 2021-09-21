package SopraAJC.NotreProjet.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Transformation  extends Batiment{

	@OneToMany(mappedBy = "transformation")
	@JsonView(JsonViews.BatimentWithCoutAndListeTransformationRessource.class)
	private List<TransformationRessource> transformationRessouce;
	
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
