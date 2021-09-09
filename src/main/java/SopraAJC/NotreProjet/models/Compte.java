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
	protected String login;
	protected String password;
	protected String prenom;
	protected String nom;
	@Column(unique = true)
	protected String surnom;
	
	@OneToMany(mappedBy = "id.compte")
	protected List<Session> sessions;
	
	public Compte() {
		super();
	}
	
	public Compte(Integer id, String login, String password, String prenom, String nom, String surnom) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.prenom = prenom;
		this.nom = nom;
		this.surnom = surnom;
	}
	
	public Compte(String login, String password, String prenom, String nom, String surnom) {
		this.login = login;
		this.password = password;
		this.prenom = prenom;
		this.nom = nom;
		this.surnom = surnom;
	}
	
	public Compte(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public Compte(Integer id, String login, String password) {
		this.id = id;
		this.login = login;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
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
	
	public String getSurnom() {
		return surnom;
	}
	
	public void setSurnom(String surnom) {
		this.surnom = surnom;
	}
	
	
	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	@Override
	public String toString() {
		return "Compte [id=" + id + ", login=" + login + ", password=" + password + ", prenom=" + prenom + ", nom="
				+ nom + ", surnom=" + surnom + ", sessions=" + sessions + "]";
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
