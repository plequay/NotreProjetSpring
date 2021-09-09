package SopraAJC.NotreProjet.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "session_ressource")
public class SessionRessource {

	@EmbeddedId
	private SessionRessourceKey id;
		    
    private int quantite;

    public SessionRessource() {
    	
    }

    public SessionRessource(SessionRessourceKey id, int quantite) {
		super();
		this.id = id;
		this.quantite = quantite;
	}

	public SessionRessource(int quantite) {
		super();
		this.quantite = quantite;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionRessource that = (SessionRessource) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public SessionRessourceKey getId() {
        return id;
    }

    public void setId(SessionRessourceKey id) {
        this.id = id;
    }

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

}
