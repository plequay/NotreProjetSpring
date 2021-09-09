package SopraAJC.NotreProjet.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "cout_batiment")
public class CoutBatiment {

	@EmbeddedId
    notreProjetBack.model.CoutBatimentKey id;
	
	private int cout;
	
	public CoutBatiment() {
	}
	
	public CoutBatiment(notreProjetBack.model.CoutBatimentKey id, int cout) {
		this.id = id;
		this.cout = cout;
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
		CoutBatiment other = (CoutBatiment) obj;
		return Objects.equals(id, other.id);
	}

	public notreProjetBack.model.CoutBatimentKey getId() {
		return id;
	}

	public void setId(notreProjetBack.model.CoutBatimentKey id) {
		this.id = id;
	}

	public int getCout() {
		return cout;
	}

	public void setCout(int cout) {
		this.cout = cout;
	}
	
}
