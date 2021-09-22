package SopraAJC.NotreProjet.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonView;

import SopraAJC.NotreProjet.exceptions.SessionRessourceException;
import SopraAJC.NotreProjet.models.JsonViews;
import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.models.SessionBatiment;
import SopraAJC.NotreProjet.models.SessionRessource;
import SopraAJC.NotreProjet.models.TransformationRessource;
import SopraAJC.NotreProjet.repositories.CompteRepository;
import SopraAJC.NotreProjet.repositories.PartieRepository;
import SopraAJC.NotreProjet.repositories.SessionRepository;
import SopraAJC.NotreProjet.repositories.SessionRessourceRepository;
import SopraAJC.NotreProjet.repositories.TransformationRepository;
import SopraAJC.NotreProjet.repositories.TransformationRessourceRepository;
import SopraAJC.NotreProjet.services.GestionBatimentService;
import SopraAJC.NotreProjet.services.GestionRessourceService;
import SopraAJC.NotreProjet.services.SessionService;


@RestController
@RequestMapping("/api/sessionressource")
@CrossOrigin(origins = "*")
public class SessionRessourceRestController {

	@Autowired
	private GestionRessourceService gestionRessourceservice;
	
	@Autowired 
	private GestionBatimentService gestionBatimentservice;
	
	@Autowired
	private SessionRepository sessionRepo;
	
	@Autowired
	private TransformationRepository transfoRepo;
	
	@Autowired 
	private PartieRepository partieRepo;
	
	@Autowired 
	private CompteRepository compteRepo;
	
	@Autowired
	private SessionService sessionService;
	@Autowired
	private SessionRessourceRepository sessionResRepo;
	
	@Autowired
	private GestionRessourceService gestionRessourceService;
	
	@GetMapping("/{idp}&{idc}")
	public List<SessionRessource> getBySession(@PathVariable Integer idp, @PathVariable Integer idc) {		
		Optional <Session> opt = sessionService.findSession(idp, idc );
		if(opt.isPresent()) {
			return sessionResRepo.findBySession(opt.get());
		}
		return null;
		
	}

	
	@Autowired
	private TransformationRessourceRepository transformationRessourceRepo;
	
	@GetMapping("/{idPartie}/{idCompte}")
	@JsonView(JsonViews.SessionWithSessionRessource.class)
	public List<SessionRessource> getSessionRessourceBySession(@PathVariable Integer idPartie,@PathVariable Integer idCompte){
		return gestionRessourceService.getSessionRessourceBySession(sessionRepo.findByPartieAndCompte(partieRepo.findById(idPartie).get(), compteRepo.findById(idCompte).get()).get());
	}
	
	//Piocher des cartes en début de tour
	@GetMapping("/piocher/{idp}&{idc}")
	@ResponseStatus(HttpStatus.OK)
	public String piocher(@PathVariable Integer idp, @PathVariable Integer idc) {
		Optional <Session> opt = sessionService.findSession(idp, idc);
		System.out.println(opt.get());
		if(opt.isPresent()) {
			String message = gestionRessourceservice.piocherRessource(opt.get());
			return message;
		}
		
		throw new SessionRessourceException();
	}
	
	//Produire les ressources grâce aux batiments de production
	@GetMapping("/productionRessource/{idp}&{idc}")
	@ResponseStatus(HttpStatus.OK)
	public List<String> productionRessource(@PathVariable Integer idp, @PathVariable Integer idc){
		Optional <Session> opt = sessionService.findSession(idp, idc);
		
		if(opt.isPresent()) {
			List<String> message = gestionRessourceservice.productionRessource(opt.get());
			return message;
		}
		
		throw new SessionRessourceException();
	}
	
	//Récupérer la liste des batiments de Transformation à disposition
	@GetMapping("/listeBatimentTransformation")
	@ResponseStatus(HttpStatus.OK)
	@JsonView(JsonViews.SessionBatimentWithBatiment.class)
	public List<SessionBatiment> listeBatimentTransformation(@PathVariable Integer idp, @PathVariable Integer idc){
		Optional <Session> opt = sessionService.findSession(idp, idc);
		
		if(opt.isPresent()) {
			List<SessionBatiment> listeBatimentTransformation = gestionRessourceservice.listeBatimentTransformation(opt.get());
			return listeBatimentTransformation;
		}
		
		throw new SessionRessourceException();
	}
	
	//Récupérer la liste des TransformationRessource possible pour le batiment de transformation séléctionné
	@GetMapping("/listeTransformationRessource/{idBatiment}")
	@ResponseStatus(HttpStatus.OK)
	@JsonView(JsonViews.TransformationRessourceWithBatimentAndRessources.class)
	public List<TransformationRessource> listeBatimentTransformation(@Valid @PathVariable Integer idBatiment){
		List<TransformationRessource> listTransformationRessource = gestionRessourceservice.listeTransformationRessource(transfoRepo.findById(idBatiment).get()) ;
		return listTransformationRessource;
	}
	
	//Récupérer une TransformationRessource
		@GetMapping("/transformationRessource/{idtr}")
		@ResponseStatus(HttpStatus.OK)
		@JsonView(JsonViews.TransformationRessourceWithBatimentAndRessources.class)
		public TransformationRessource getTransformationRessource(@Valid @PathVariable Integer idtr){
			TransformationRessource transformationRessource = gestionRessourceservice.getTransformationRessourceById(idtr) ;
			return transformationRessource;
		}
	
	//Transformation d'une ressource
	@PostMapping("/transformationRessource/{idp}/{idc}/{idtr}/{quantite}")
	@ResponseStatus(HttpStatus.CREATED)

	public void transformationRessource(@PathVariable Integer idp, @PathVariable Integer idc, @Valid @PathVariable Integer idtr, @PathVariable Integer quantite) {
		
		//Session Valide
		Optional <Session> session = sessionService.findSession(idp, idc);
		if(!session.isPresent()) {
			throw new SessionRessourceException();	
		} 	
		
		//quantite de ressource suffissante
		SessionRessource sessionRessource =gestionRessourceservice.getSessionRessource(session.get(), transformationRessourceRepo.findById(idtr).get().getRessourceLost());
		if(!gestionRessourceservice.verificationQuantiteRessource(sessionRessource, quantite)) {
			throw new SessionRessourceException();
		}
		
		//Batiment de Transformation possédé
		if(!gestionBatimentservice.verificationPossessionbatiment(session.get(), transformationRessourceRepo.findById(idtr).get().getTransformation())) {
			throw new SessionRessourceException();
		}
		
		gestionRessourceservice.transformationRessource(session.get(), transformationRessourceRepo.findById(idtr).get(), quantite);
	}

}

