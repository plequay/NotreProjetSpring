package SopraAJC.NotreProjet.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="typeCompte")
public abstract class Compte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	@Column(name = "username", length = 150, unique = true, nullable = false)
	protected String username;
	@Column(name = "password", length = 255, nullable = false)
	protected String password;
	@Enumerated(EnumType.STRING)
	@Column(name = "role", length = 30, nullable = false)
	private Role role;
	protected String prenom;
	protected String nom;
	
	@OneToMany(mappedBy = "id.compte")
	protected List<Session> sessions;
	
	public Compte() {
		super();
	}
	
	public Compte(Integer id, String username, String password, String prenom, String nom, Role role){
		this.id = id;
		this.username = username;
		this.password = password;
		this.prenom = prenom;
		this.nom = nom;
		this.role = role;
	}
	
	public Compte(String username, String password, String prenom, String nom, Role role) {
		this.username = username;
		this.password = password;
		this.prenom = prenom;
		this.nom = nom;
		this.role = role;
	}

	public Compte(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Compte(Integer id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Compte{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", prenom='" + prenom + '\'' +
				", nom='" + nom + '\'' +
				", sessions=" + sessions +
				'}';
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
		Compte other = (Compte) obj;
		return id == other.id;
	}
}
