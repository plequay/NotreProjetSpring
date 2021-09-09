package SopraAJC.NotreProjet.models;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SessionKey implements Serializable {

	@ManyToOne
	@JoinColumn(name = "partie_id", foreignKey = @ForeignKey(name = "session_partie_id_pk"))
	@JsonView({JsonViews.SessionWithPartie.class, JsonViews.SessionWithPartieAndCompte.class})
	private Partie partie;

    @ManyToOne
    @JoinColumn(name = "compte_id", foreignKey = @ForeignKey(name = "session_compte_id_pk"))
	@JsonView({JsonViews.SessionWithCompte.class, JsonViews.SessionWithPartieAndCompte.class, JsonViews.PartieWithSession.class})
	private Compte compte;
	
    public SessionKey() {
    	
    }

	public SessionKey(Partie partie, Compte compte) {
		this.partie = partie;
		this.compte = compte;
	}

	public Partie getPartie() {
		return partie;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	@Override
	public int hashCode() {
		return Objects.hash(compte, partie);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SessionKey other = (SessionKey) obj;
		return Objects.equals(compte, other.compte) && Objects.equals(partie, other.partie);
	}
	
}
