package SopraAJC.NotreProjet.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonView;

import SopraAJC.NotreProjet.models.JsonViews.SessionWithAll;

import java.util.Objects;

@Entity
@Table(name = "session_batiment")
public class SessionBatiment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.Common.class)
    private Integer id;

    @ManyToOne
    @JsonView(JsonViews.SessionBatimentWithSession.class)
    private Session session;

    @ManyToOne
    @JoinColumn(name = "batiment_id", foreignKey = @ForeignKey(name = "session_batiment_id_pk"))
    @JsonView({JsonViews.SessionBatimentWithBatiment.class, JsonViews.SessionWithSessionBatiment.class, SessionWithAll.class})
    private Batiment batiment;
    
    @JsonView(JsonViews.Common.class)
    private double pointsDeVie;
    
    @JsonView(JsonViews.Common.class)
    private double pointsDAttaque;
    
    @JsonView(JsonViews.Common.class)
	protected int level=1;
    
    
    public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@JsonView(JsonViews.Common.class)
	private boolean used = false;
    
    public SessionBatiment() {
    }

    public SessionBatiment(Session session, Batiment batiment, double pointsDeVie, double pointsDAttaque) {
		this.session = session;
		this.batiment = batiment;
		this.pointsDeVie = pointsDeVie;
		this.pointsDAttaque = pointsDAttaque;
	}
    
    public SessionBatiment(Session session, Batiment batiment, double pointsDeVie) {
		this.session = session;
		this.batiment = batiment;
		this.pointsDeVie = pointsDeVie;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionBatiment that = (SessionBatiment) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

	public Batiment getBatiment() {
		return batiment;
	}

	public void setBatiment(Batiment batiment) {
		this.batiment = batiment;
	}

	public double getPointsDeVie() {
		return pointsDeVie;
	}

	public void setPointsDeVie(double pointsDeVie) {
		this.pointsDeVie = pointsDeVie;
	}

	public double getPointsDAttaque() {
		return pointsDAttaque;
	}

	public void setPointsDAttaque(double pointsDAttaque) {
		this.pointsDAttaque = pointsDAttaque;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}
	
}
