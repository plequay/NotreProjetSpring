package SopraAJC.NotreProjet.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Session {
	
	@EmbeddedId
    private notreProjetBack.model.SessionKey id;
    
    @Column(name = "a_joue_le_tours")
    private boolean aJoueLeTours;

    @Column(name = "tour_en_tours")
    private boolean tourEnCours;

    @Column(name = "a_commence")
    private boolean aCommence;

    private int def;

    private int att;

    @OneToMany (mappedBy = "session")
    private List<notreProjetBack.model.SessionBatiment> sessionBatiment;

    @OneToMany (mappedBy = "id.session")
    private Set<SessionRessource> sessionRessource;

    public Session() {
    }

    public Session(notreProjetBack.model.SessionKey id) {
		super();
		this.id = id;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return id.equals(session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public notreProjetBack.model.SessionKey getId() {
        return id;
    }

    public void setId(notreProjetBack.model.SessionKey id) {
        this.id = id;
    }

    public boolean isaJoueLeTours() {
        return aJoueLeTours;
    }

    public void setaJoueLeTours(boolean aJoueLeTours) {
        this.aJoueLeTours = aJoueLeTours;
    }

    public boolean isTourEnCours() {
        return tourEnCours;
    }

    public void setTourEnCours(boolean tourEnCours) {
        this.tourEnCours = tourEnCours;
    }

    public boolean isaCommence() {
        return aCommence;
    }

    public void setaCommence(boolean aCommence) {
        this.aCommence = aCommence;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getAtt() {
        return att;
    }

    public void setAtt(int att) {
        this.att = att;
    }

	public List<notreProjetBack.model.SessionBatiment> getSessionBatiment() {
		return sessionBatiment;
	}

	public void setSessionBatiment(List<notreProjetBack.model.SessionBatiment> sessionBatiment) {
		this.sessionBatiment = sessionBatiment;
	}

	public Set<SessionRessource> getSessionRessource() {
		return sessionRessource;
	}

	public void setSessionRessource(Set<SessionRessource> sessionRessource) {
		this.sessionRessource = sessionRessource;
	}

}
