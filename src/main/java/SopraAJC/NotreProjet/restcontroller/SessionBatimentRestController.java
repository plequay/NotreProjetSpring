package SopraAJC.NotreProjet.restcontroller;

import java.util.List;

import SopraAJC.NotreProjet.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import SopraAJC.NotreProjet.exceptions.SessionBatimentException;
import SopraAJC.NotreProjet.repositories.BatimentRepository;
import SopraAJC.NotreProjet.repositories.CompteRepository;
import SopraAJC.NotreProjet.repositories.PartieRepository;
import SopraAJC.NotreProjet.repositories.SessionBatimentRepository;
import SopraAJC.NotreProjet.repositories.SessionRepository;
import SopraAJC.NotreProjet.services.ConstructionBatimentService;
import SopraAJC.NotreProjet.services.GestionRessourceService;


@RestController
@RequestMapping("/api/sessionbatiment")
@CrossOrigin(origins = "*")
public class SessionBatimentRestController {

	@Autowired 
	private SessionBatimentRepository sessionBatRepo;
	
	@Autowired 
	private SessionRepository sessionRepo;
	
	@Autowired 
	private ConstructionBatimentService construction;
	
	@Autowired
	private PartieRepository partieRepo;
	
	@Autowired 
	private CompteRepository compteRepo;
	
	@Autowired 
	private BatimentRepository batimentRepo;
	
	@Autowired 
	private GestionRessourceService gestionRessource;
	
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
	
//	@GetMapping("/construction/{idPartie}/{idCompte}")
//	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
//	public List<Batiment> BatimentConstructible(@PathVariable Integer idPartie, @PathVariable Integer idCompte){
//		Session session =sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idCompte).get()).get();
//		return construction.Constructible(session);
//	}
//	
//	@GetMapping("/amelioration/{idPartie}/{idCompte}")
//	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
//	public List<SessionBatiment> batimentAmeliorable(@PathVariable Integer idPartie, @PathVariable Integer idCompte){
//		Session session =sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idCompte).get()).get();

	@GetMapping("/construction/{idPartie}/{idCompte}")
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
//<<<<<<< HEAD
//	public List<Batiment> BatimentConstructible(@PathVariable Integer idPartie, @PathVariable Integer idCompte){
//		return construction.Constructible(sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idCompte).get()).get());
//=======
	public List<Batiment> BatimentConstructible(@PathVariable Long idPartie, @PathVariable Long idCompte){
		Session session =sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idCompte).get()).get();
		return construction.Constructible(session);
//>>>>>>> nico
	}
	
	@GetMapping("/amelioration/{idPartie}/{idCompte}")
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
//<<<<<<< HEAD
//	public List<SessionBatiment> batimentAmeliorable(@PathVariable Integer idPartie, @PathVariable Integer idCompte){
//		return construction.Ameliorable(sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idCompte).get()).get());
//=======
	public List<SessionBatiment> batimentAmeliorable(@PathVariable Long idPartie, @PathVariable Long idCompte){
		Session session =sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idCompte).get()).get();
		return construction.Ameliorable(session);
//>>>>>>> nico
	}
	
	@GetMapping("/transformation/{idPartie}/{idCompte}")
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
	public List<SessionBatiment> batimentTransformation(@PathVariable Long idPartie, @PathVariable Long idCompte){
		return gestionRessource.listeBatimentTransformation(sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idCompte).get()).get());
	}

	@PostMapping("/construction/{idPartie}/{idCompte}/{idBat}")
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
	public SessionBatiment construction(@PathVariable Long idPartie,@PathVariable Long idCompte, @PathVariable Integer idBat) {
		
		if(construction.verificationConstructible(batimentRepo.findById(idBat).get(), sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idCompte).get()).get())) {
			SessionBatiment sb= construction.constructBat(batimentRepo.findById(idBat).get(), sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idCompte).get()).get());
			return sb;
		} else {
			return null;
		}
	}
	
	@PutMapping("/amelioration/{id}")		//Poser l'ID de sessionBatiment à ameliorer
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
	public SessionBatiment amelioration(@PathVariable Integer id) {
		if(construction.verificationAmeliorable(sessionBatRepo.findById(id).get())) {
			SessionBatiment sb= construction.ameliorationBat(sessionBatRepo.findById(id).get());
			return sb;
		} 
		throw new SessionBatimentException();
		
	}
	
}
