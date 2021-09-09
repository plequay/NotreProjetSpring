package SopraAJC.NotreProjet.services;

import SopraAJC.NotreProjet.exceptions.CompteException;
import SopraAJC.NotreProjet.repositories.AdminRepository;
import SopraAJC.NotreProjet.repositories.CompteRepository;
import SopraAJC.NotreProjet.repositories.JoueurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SopraAJC.NotreProjet.models.Admin;
import SopraAJC.NotreProjet.models.Compte;
import SopraAJC.NotreProjet.models.Joueur;

import java.util.Optional;


@Service
public class CompteService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompteService.class);

	@Autowired
	CompteRepository compteRepository;

	@Autowired
	JoueurRepository joueurRepository;

	@Autowired
	AdminRepository adminRepository;

	public Compte compteExistsInDB(Integer id){
		LOGGER.info("id: " + id);
		Optional<Compte> opt = compteRepository.findById(id);
		if (opt.isPresent()){
			return opt.get();
		}
		throw new CompteException();
	}

/*	public static Joueur saveNewJoueur(Joueur joueur){
		joueurRepository.save(joueur);
		return joueur;
	}
	
	public static Admin saveNewAdmin(Admin admin){
		adminRepository.save(admin);
		return admin;
	}
	
	public static Compte checkConnexion(String login, String password) {
		Compte compte = null;
		if(compteRepository.findByUsernameAndPassword(login, password).isPresent()) {
			compte = compteRepository.findByUsernameAndPassword(login, password).get();
		}
		return compte;
		//gerer joueur et admin
	}	*/
}
