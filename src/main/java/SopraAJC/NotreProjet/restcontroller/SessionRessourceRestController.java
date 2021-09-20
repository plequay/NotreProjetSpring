package SopraAJC.NotreProjet.restcontroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SopraAJC.NotreProjet.exceptions.SessionRessourceException;
import SopraAJC.NotreProjet.models.Compte;
import SopraAJC.NotreProjet.models.Partie;
import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.repositories.CompteRepository;
import SopraAJC.NotreProjet.repositories.PartieRepository;
import SopraAJC.NotreProjet.repositories.SessionRepository;
import SopraAJC.NotreProjet.services.CompteService;
import SopraAJC.NotreProjet.services.GestionRessourceService;
import SopraAJC.NotreProjet.services.PartieService;

@RestController
@RequestMapping("/api/sessionressource")
public class SessionRessourceRestController {

	@Autowired
	private GestionRessourceService gestionRessourceservice;
	
	//TODO : Utilisation du repo en attendant le pull du service session
	@Autowired
	private SessionRepository sessionRepo;
	
	
	@Autowired
	private CompteService compteService;
	
	@Autowired
	private PartieService partieService;
	
	
	
	@GetMapping("/piocher/{idp}&{idc}")
	public String piocher(@PathVariable Integer idp, @PathVariable Integer idc) {
		
		Compte compte = compteService.compteExistsInDB(idc);
		Partie partie = partieService.partieExistsInDB(idp);
		Optional<Session> opt=sessionRepo.findByPartieAndCompte(partie, compte);
		
		if(opt.isPresent()) {
			String message = gestionRessourceservice.piocherRessource(opt.get());
			return message;
		}
		
		throw new SessionRessourceException();
	}
}
