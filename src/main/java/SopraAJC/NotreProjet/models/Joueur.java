package SopraAJC.NotreProjet.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("joueur")
public class Joueur extends Compte{

	public Joueur() {
	}
	
	public Joueur (String login, String password){
		super(login, password);
	}
	
	public Joueur (Long id, String login, String password){
		super(id, login, password);
	}
	
	public Joueur (String username, String password, String prenom, String nom){
		super(username, password, prenom, nom, Role.ROLE_JOUEUR);
	}
	
	public Joueur (Long id, String login, String password, String prenom, String nom, Role role){
		super(id, login, password, prenom, nom, Role.ROLE_JOUEUR);
	}
}
