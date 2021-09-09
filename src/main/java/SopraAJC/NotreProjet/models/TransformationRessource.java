package SopraAJC.NotreProjet.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TransformationRessource {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private Transformation transformation;
	@ManyToOne
	private Ressource ressourceLost;
	@ManyToOne
	private Ressource ressourceWin;
	
	public TransformationRessource() {
		
	}

	public TransformationRessource(Transformation transformation, Ressource ressourceLost, Ressource ressourceWin) {
		super();
		this.transformation = transformation;
		this.ressourceLost = ressourceLost;
		this.ressourceWin = ressourceWin;
	}
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the transformation
	 */
	public Transformation getTransformation() {
		return transformation;
	}
	/**
	 * @param transformation the transformation to set
	 */
	public void setTransformation(Transformation transformation) {
		this.transformation = transformation;
	}
	/**
	 * @return the ressourceLost
	 */
	public Ressource getRessourceLost() {
		return ressourceLost;
	}
	/**
	 * @param ressourceLost the ressourceLost to set
	 */
	public void setRessourceLost(Ressource ressourceLost) {
		this.ressourceLost = ressourceLost;
	}
	/**
	 * @return the ressourceWin
	 */
	public Ressource getRessourceWin() {
		return ressourceWin;
	}
	/**
	 * @param ressourceWin the ressourceWin to set
	 */
	public void setRessourceWin(Ressource ressourceWin) {
		this.ressourceWin = ressourceWin;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransformationRessource other = (TransformationRessource) obj;
		return Objects.equals(id, other.id);
	}	
}
