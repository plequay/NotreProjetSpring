package SopraAJC.NotreProjet.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SopraAJC.NotreProjet.repositories.CompteRepository;
import SopraAJC.NotreProjet.repositories.PartieRepository;
import SopraAJC.NotreProjet.repositories.SessionRepository;
import SopraAJC.NotreProjet.services.GestionRessourceService;

@RestController
@RequestMapping("/api/sessionressource")
public class SessionsRessourceRestController {

	@Autowired
	private GestionRessourceService gestionRessourceservice;
	
	//TODO : Utilisation du repo en attendant le pull du service session
	@Autowired
	private SessionRepository sessionRepo;
	
	@Autowired
	private PartieRepository partieRepo;
	
	@Autowired
	private CompteRepository compteRepo;
	
	
	@GetMapping("/piocher/{idp}&{idc}")
	public String piocher(@PathVariable Integer idp, @PathVariable Integer idc) {
		String message = gestionRessourceservice.piocherRessource(
				sessionRepo.findByPartieAndCompte(
					partieRepo.findById(idp).get(), 
					compteRepo.findById(idc).get())
				.get());
		return message;
	}
}
