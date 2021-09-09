package SopraAJC.NotreProjet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.support.RepositoryComposition;
import org.springframework.stereotype.Service;

import SopraAJC.NotreProjet.models.Admin;
import SopraAJC.NotreProjet.models.Compte;
import SopraAJC.NotreProjet.models.Joueur;
import SopraAJC.NotreProjet.repositories.AdminRepository;
import SopraAJC.NotreProjet.repositories.CompteRepository;
import SopraAJC.NotreProjet.repositories.JoueurRepository;


@Service
public class CompteService {

	@Autowired
	private static CompteRepository compteRepo;
	
	@Autowired
	private static JoueurRepository joueurRepo;
	
	@Autowired
	private static AdminRepository adminRepo;
	
	public static Joueur saveNewJoueur(Joueur joueur){
		joueurRepo.save(joueur);
		return joueur;
	}
	
	public static Admin saveNewAdmin(Admin admin){
		adminRepo.save(admin);
		return admin;
	}
	
	public static Compte checkConnexion(String login, String password) {
		Compte compte = null;
		if(compteRepo.findByUsernameAndPassword(login, password).isPresent()) {
			compte = compteRepo.findByUsernameAndPassword(login, password).get();
		}
		return compte;
		//gerer joueur et admin
	}	
}
