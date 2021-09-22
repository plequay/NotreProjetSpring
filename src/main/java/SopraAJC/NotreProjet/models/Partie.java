package SopraAJC.NotreProjet.models;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Partie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViews.Common.class)
    private  Integer id;

    @JsonView(JsonViews.Common.class)
    private String description;

    @OneToMany(mappedBy = "id.partie")
    @JsonView(JsonViews.PartieWithSession.class)
    private List<Session> sessions = new ArrayList<>();

    public Partie() {
    }

    public Partie(String description) {
        this.description = description;
    }

    public Partie(Integer id, String description, List<Session> sessions) {
        this.id = id;
        this.description = description;
        this.sessions = sessions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partie partie = (Partie) o;
        return id.equals(partie.id);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

}
