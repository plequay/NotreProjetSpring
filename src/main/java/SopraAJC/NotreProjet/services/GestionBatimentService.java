package SopraAJC.NotreProjet.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import SopraAJC.NotreProjet.models.Batiment;
import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.models.SessionBatiment;
import SopraAJC.NotreProjet.repositories.SessionBatimentRepository;
import org.springframework.stereotype.Service;

@Service
public class GestionBatimentService {

	@Autowired
	SessionBatimentRepository sessionBatimentRepository;
	
	/*
	    * Verification de la possession d'un batiment 
	    * */
	public boolean verificationPossessionbatiment(Session session, Batiment batiment) {
		Optional<SessionBatiment> opt = sessionBatimentRepository.findBySessionAndBatiment(session, batiment);
		return opt.isPresent();
	}

}
