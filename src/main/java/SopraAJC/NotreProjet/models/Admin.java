package SopraAJC.NotreProjet.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class Admin extends Compte{

	public Admin() {
	}
	
	public Admin(String login, String password) {
		super(login, password);
	}

	public Admin(Long id,String login, String password) {
		super(id,login, password);
	}
		
	public Admin (Long id, String username, String password, String prenom, String nom)
	{
		super(id, username, password, prenom, nom, Role.ROLE_ADMIN);
	}
	
	public Admin (String username, String password, String prenom, String nom)
	{
		super(username, password, prenom, nom, Role.ROLE_ADMIN);
	}
}
