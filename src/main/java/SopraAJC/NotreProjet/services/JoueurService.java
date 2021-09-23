package SopraAJC.NotreProjet.services;

import SopraAJC.NotreProjet.exceptions.CompteException;
import SopraAJC.NotreProjet.models.Compte;
import SopraAJC.NotreProjet.models.Joueur;
import SopraAJC.NotreProjet.repositories.AdminRepository;
import SopraAJC.NotreProjet.repositories.CompteRepository;
import SopraAJC.NotreProjet.repositories.JoueurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class JoueurService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JoueurService.class);

	@Autowired
	JoueurRepository joueurRepository;

	public Joueur joueurExistsInDB(Integer id){
		LOGGER.info("id: " + id);
		Optional<Joueur> opt = joueurRepository.findById(id);
		if (opt.isPresent()){
			return opt.get();
		}
		throw new CompteException();
	}

	public Joueur joueurIsInDatabaseByUsername(String username){
		LOGGER.info("username: " + username);
		Optional<Joueur> opt = joueurRepository.findByUsername(username);
		if (opt.isPresent()){
			return opt.get();
		}
		throw new CompteException();
	}

	public Boolean isJoueurUsernameInDatabase(String username) {
		LOGGER.info("username: " + username);
		Optional<Joueur> opt = joueurRepository.findByUsername(username);
		if (opt.isPresent()){
			return true;
		}
		return false;
	}


}
