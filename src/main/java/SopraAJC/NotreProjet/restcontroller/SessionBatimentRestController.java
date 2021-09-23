package SopraAJC.NotreProjet.restcontroller;

import java.util.List;

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
import SopraAJC.NotreProjet.models.Attaque;
import SopraAJC.NotreProjet.models.Batiment;
import SopraAJC.NotreProjet.models.JsonViews;
import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.models.SessionBatiment;
import SopraAJC.NotreProjet.repositories.BatimentRepository;
import SopraAJC.NotreProjet.repositories.CompteRepository;
import SopraAJC.NotreProjet.repositories.PartieRepository;
import SopraAJC.NotreProjet.repositories.SessionBatimentRepository;
import SopraAJC.NotreProjet.repositories.SessionRepository;
import SopraAJC.NotreProjet.services.AttaqueService;
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
	
	@Autowired
	private AttaqueService attaqueService;
	
	@GetMapping("")
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
	public List<SessionBatiment> getAllB() {
		return sessionBatRepo.findAll();	
	}
	
	@GetMapping("/{idPartie}/{idCompte}")
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
	public List<SessionBatiment> getAllBySession(@PathVariable Integer idPartie, @PathVariable Integer idCompte) {
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
	public List<Batiment> BatimentConstructible(@PathVariable Integer idPartie, @PathVariable Integer idCompte){
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
	public List<SessionBatiment> batimentAmeliorable(@PathVariable Integer idPartie, @PathVariable Integer idCompte){
		Session session =sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idCompte).get()).get();
		return construction.Ameliorable(session);
//>>>>>>> nico
	}
	
	@GetMapping("/transformation/{idPartie}/{idCompte}")
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
	public List<SessionBatiment> batimentTransformation(@PathVariable Integer idPartie, @PathVariable Integer idCompte){
		return gestionRessource.listeBatimentTransformation(sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idCompte).get()).get());
	}
	
	@GetMapping("/production/{idPartie}/{idCompte}")
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
	public List<SessionBatiment> batimentProduction(@PathVariable Integer idPartie, @PathVariable Integer idCompte){
		return gestionRessource.listeBatimentProduction(sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idCompte).get()).get());
	}
	
	private Session actuDefAttPlayer(Session session) {
		double att = 0;
		double def =0;
		for (SessionBatiment sb : session.getSessionBatiment()) {
			
			def+=sb.getPointsDeVie();
			att+=sb.getPointsDAttaque();
		}
		session.setAtt(att);
		session.setDef(def);
		return sessionRepo.save(session);
	}
	
	
	@GetMapping("attaque/{idPartie}/{idCompte}")
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
	public List<SessionBatiment> getBatimentsAttaque(@PathVariable Integer idPartie, @PathVariable Integer idCompte){
		return attaqueService.getBatimentAttaqueBySession(sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idCompte).get()).get());
	}
	@GetMapping("defense/{idPartie}/{idCompte}")
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
	public List<SessionBatiment> getBatimentsDefense(@PathVariable Integer idPartie, @PathVariable Integer idCompte){
		return sessionBatRepo.findBySessionAndBatimentDefense(sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idCompte).get()).get());
	}

	@PostMapping("/construction/{idPartie}/{idCompte}/{idBat}")
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
	public SessionBatiment construction(@PathVariable Integer idPartie,@PathVariable Integer idCompte, @PathVariable Integer idBat) {
		
		if(construction.verificationConstructible(batimentRepo.findById(idBat).get(), sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idCompte).get()).get())) {
			SessionBatiment sb= construction.constructBat(batimentRepo.findById(idBat).get(), sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idCompte).get()).get());
			actuDefAttPlayer(sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idCompte).get()).get());
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
			actuDefAttPlayer(sb.getSession());
			return sb;
		} 
		throw new SessionBatimentException();
		
	}
	
	@PutMapping("/attaque/{idPartie}/{idAttack}/all/{idTarget}/all")
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
	public void attaqueAllWithAll(@PathVariable Integer idPartie, @PathVariable Integer idAttack, @PathVariable Integer idTarget){
		Session attaquant = sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idAttack).get()).get();
		Session cible = sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idTarget).get()).get();
		attaqueService.attackAllBatimentWithAllBatiment(attaquant, cible);
		actuDefAttPlayer(sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idTarget).get()).get());
	}
	
	@PutMapping("/attaque/{idPartie}/{idAttack}/{idBatAtt}/{idTarget}/all")
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
	public void attaqueAllWithOne(@PathVariable Integer idPartie, @PathVariable Integer idAttack, @PathVariable Integer idBatAtt, @PathVariable Integer idTarget){
		Session attaquant = sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idAttack).get()).get();
		Session cible = sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idTarget).get()).get();
		attaqueService.attackAllBatimentWithOneBatiment(attaquant,idBatAtt,cible);
		actuDefAttPlayer(sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idTarget).get()).get());
	}
	
	@PutMapping("/attaque/{idPartie}/{idAttack}/all/{idTarget}/{idBatTar}")
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
	public void attaqueOneWithAll(@PathVariable Integer idPartie, @PathVariable Integer idAttack, @PathVariable Integer idTarget, @PathVariable Integer idBatTar){
		Session attaquant = sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idAttack).get()).get();
		Session cible = sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idTarget).get()).get();
		attaqueService.attackOneBatimentWithAllBatiment(attaquant, cible, idBatTar);
		actuDefAttPlayer(sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idTarget).get()).get());
	}
	
	@PutMapping("/attaque/{idPartie}/{idAttack}/{idBatAtt}/{idTarget}/{idBatTar}")
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
	public void attaqueOneWithOne(@PathVariable Integer idPartie, @PathVariable Integer idAttack, @PathVariable Integer idBatAtt, @PathVariable Integer idTarget, @PathVariable Integer idBatTar){
		Session attaquant = sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idAttack).get()).get();
		Session cible = sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idTarget).get()).get();
		attaqueService.attackOneBatimentWithOneBatiment(attaquant,idBatAtt,cible,idBatTar);
		actuDefAttPlayer(sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idTarget).get()).get());
	}
	
	
}
