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
	
	public Joueur (Integer id, String login, String password){
		super(id, login, password);
	}
	
	public Joueur (String login, String password, String prenom, String nom, String surnom){
		super(login,password, prenom, nom, surnom);
	}
	
	public Joueur (Integer id, String login, String password, String prenom, String nom, String surnom){
		super(id,login,password,prenom, nom, surnom);
	}
}
