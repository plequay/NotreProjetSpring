package SopraAJC.NotreProjet.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Embeddable
public class CoutBatimentKey implements Serializable {
	
	@ManyToOne
	Batiment batiment;
	
	@ManyToOne
	@JsonView(JsonViews.BatimentWithCout.class)
	Ressource ressource;
	
	public CoutBatimentKey() {
	}
	
	public CoutBatimentKey(Batiment batiment, Ressource ressource) {
		this.batiment = batiment;
		this.ressource = ressource;
	}

	@Override
	public int hashCode() {
		return Objects.hash(batiment, ressource);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoutBatimentKey other = (CoutBatimentKey) obj;
		return Objects.equals(batiment, other.batiment) && Objects.equals(ressource, other.ressource);
	}

	public Batiment getBatiment() {
		return batiment;
	}

	public void setBatiment(Batiment batiment) {
		this.batiment = batiment;
	}

	public Ressource getRessource() {
		return ressource;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}
	
}
