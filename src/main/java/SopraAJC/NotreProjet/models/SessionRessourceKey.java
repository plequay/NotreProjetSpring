package SopraAJC.NotreProjet.models;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SessionRessourceKey implements Serializable{

	@ManyToOne
    private Session session;
	
	@ManyToOne
    @JoinColumn(name = "ressource_id", foreignKey = @ForeignKey(name = "session_ressource_id_pk"))
    private Ressource ressource;

	public SessionRessourceKey() {
		
	}

	public SessionRessourceKey(Session session, Ressource ressource) {
		this.session = session;
		this.ressource = ressource;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Ressource getRessource() {
		return ressource;
	}

	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ressource, session);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SessionRessourceKey other = (SessionRessourceKey) obj;
		return Objects.equals(ressource, other.ressource) && Objects.equals(session, other.session);
	}
	
}
