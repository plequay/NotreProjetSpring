package SopraAJC.NotreProjet.restcontroller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import SopraAJC.NotreProjet.exceptions.SessionBatimentException;
import SopraAJC.NotreProjet.models.Batiment;
import SopraAJC.NotreProjet.models.CompteUserDetails;
import SopraAJC.NotreProjet.models.JsonViews;
import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.models.SessionBatiment;
import SopraAJC.NotreProjet.repositories.CompteRepository;
import SopraAJC.NotreProjet.repositories.PartieRepository;
import SopraAJC.NotreProjet.repositories.SessionBatimentRepository;
import SopraAJC.NotreProjet.repositories.SessionRepository;
import SopraAJC.NotreProjet.services.ConstructionBatimentService;


@RestController
@RequestMapping("/api/sessionbatiment")
public class SessionBatimentRestController {

	@Autowired SessionBatimentRepository sessionBatRepo;
	
	@Autowired SessionRepository sessionRepo;
	
	@Autowired ConstructionBatimentService construction;
	@Autowired PartieRepository partieRepo;
	@Autowired CompteRepository compteRepo;
	
	@GetMapping("")
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
	public List<SessionBatiment> getAllB() {
		return sessionBatRepo.findAll();	
	}
	
	@GetMapping("/{idPartie}/{idCompte}")
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
	public List<SessionBatiment> getAllBySession(@PathVariable Long idPartie, @PathVariable Long idCompte) {
		return sessionBatRepo.findBySession(sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idCompte).get()).get());	
	}
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
	public SessionBatiment getById (@PathVariable Integer id) {
		return sessionBatRepo.findById(id).get();
	}
	
	@GetMapping("/construction/{idPartie}/{idCompte}")
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
	public List<Batiment> BatimentConstructible(@PathVariable Long idPartie, @PathVariable Long idCompte){
		Session session =sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idCompte).get()).get();
		return construction.Constructible(session);
	}
	
	@GetMapping("/amelioration/{idPartie}/{idCompte}")
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
	public List<SessionBatiment> batimentAmeliorable(@PathVariable Long idPartie, @PathVariable Long idCompte){
		Session session =sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idCompte).get()).get();
		return construction.Ameliorable(session);
	}
	
	
	@PostMapping("")
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
	public SessionBatiment construction(@RequestBody Session session, @RequestBody Batiment bat) {
		
		if(construction.verificationConstructible(bat, session)) {
			SessionBatiment sb= construction.constructBat(bat, session);
			return sb;
		} else {
			return null;
		}
	}
	
	@PutMapping("/{id}")		//Poser l'ID de sessionBatiment à ameliorer
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
	public SessionBatiment amelioration(@PathVariable Integer id) {
		if(construction.verificationAmeliorable(sessionBatRepo.findById(id).get())) {
			SessionBatiment sb= construction.ameliorationBat(sessionBatRepo.findById(id).get());
			return sb;
		} 
		throw new SessionBatimentException();
		
	}
	
	
	
	
	
	
	
	
}
