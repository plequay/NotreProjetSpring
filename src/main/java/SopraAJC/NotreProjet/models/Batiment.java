package SopraAJC.NotreProjet.models;

import java.util.List;
import java.util.Objects;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_batiment",discriminatorType=DiscriminatorType.STRING)
public abstract class Batiment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonView(JsonViews.Common.class)
	protected Integer id;
	
	@JsonView(JsonViews.Common.class)
	protected String nom;
	
	@JsonView(JsonViews.Common.class)
	protected double pointsDefense;
	
	@JsonView(JsonViews.Common.class)
	protected boolean ameliorable;
	
	@OneToMany(mappedBy = "id.batiment")
	@JsonView(JsonViews.BatimentWithCout.class)
	protected List<CoutBatiment> coutBatiment;
	
	public Batiment() {
		
	}	
	
	public Batiment(String nom, double pointsDefense, int level, boolean ameliorable,
			List<CoutBatiment> coutBatiment) {
		super();
		this.nom = nom;
		this.pointsDefense = pointsDefense;
		this.ameliorable = ameliorable;
		this.coutBatiment = coutBatiment;
	}



	public Batiment(Integer id, String nom, double pointsDefense, int level, boolean ameliorable) {
		super();
		this.id = id;
		this.nom = nom;
		this.pointsDefense = pointsDefense;
		this.ameliorable = ameliorable;
	}

	public Batiment(String nom, double def, int level, boolean ameliorable) {
		super();
		this.nom = nom;
		this.pointsDefense = def;
		this.ameliorable = ameliorable;
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
		Batiment other = (Batiment) obj;
		return Objects.equals(id, other.id);
	}

	public boolean isAmeliorable() {
		return ameliorable;
	}

	public void setAmeliorable(boolean ameliorable) {
		this.ameliorable = ameliorable;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPointsDefense() {
		return pointsDefense;
	}

	public void setPointsDefense(double pointsDefense) {
		this.pointsDefense = pointsDefense;
	}


	public List<CoutBatiment> getCoutBatiment() {
		return coutBatiment;
	}

	public void setCost(List<CoutBatiment> coutBatiment) {
		this.coutBatiment = coutBatiment;
	}

}
