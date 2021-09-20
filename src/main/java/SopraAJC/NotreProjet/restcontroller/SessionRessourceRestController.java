package SopraAJC.NotreProjet.restcontroller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import SopraAJC.NotreProjet.exceptions.SessionRessourceException;
import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.models.SessionBatiment;
import SopraAJC.NotreProjet.models.SessionRessource;
import SopraAJC.NotreProjet.models.Transformation;
import SopraAJC.NotreProjet.models.TransformationRessource;
import SopraAJC.NotreProjet.services.GestionBatimentService;
import SopraAJC.NotreProjet.services.GestionRessourceService;
import SopraAJC.NotreProjet.services.SessionService;


@RestController
@RequestMapping("/api/sessionressource")
public class SessionRessourceRestController {

	@Autowired
	private GestionRessourceService gestionRessourceservice;
	
	@Autowired 
	private GestionBatimentService gestionBatimentservice;
	
	@Autowired
	private SessionService sessionService;
	

	
	//Piocher des cartes en début de tour
	@GetMapping("/piocher/{idp}&{idc}")
	@ResponseStatus(HttpStatus.OK)
	public String piocher(@PathVariable Integer idp, @PathVariable Integer idc) {		
		Optional <Session> opt = sessionService.findSession(idp, idc);
		
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
	public List<SessionBatiment> listeBatimentTransformation(@PathVariable Integer idp, @PathVariable Integer idc){
		Optional <Session> opt = sessionService.findSession(idp, idc);
		
		if(opt.isPresent()) {
			List<SessionBatiment> listeBatimentTransformation = gestionRessourceservice.listeBatimentTransformation(opt.get());
			return listeBatimentTransformation;
		}
		
		throw new SessionRessourceException();
	}
	
	//Récupérer la liste des TransformationRessource possible pour le batiment de transformation séléctionné
	@GetMapping("/listeTransformationRessource")
	@ResponseStatus(HttpStatus.OK)
	public List<TransformationRessource> listeBatimentTransformation(@Valid @RequestBody Transformation transformation, BindingResult br){
		if(br.hasErrors()) {
			throw new SessionRessourceException();
		}
		List<TransformationRessource> listTransformationRessource = gestionRessourceservice.listeTransformationRessource(transformation) ;
		return listTransformationRessource;
	}
	
	//Transformation d'une ressource
	@PostMapping("/transformationRessource")
	@ResponseStatus(HttpStatus.CREATED)
	public void transformationRessource(@Valid @RequestBody TransformationRessource transformationRessource, BindingResult br ,@RequestBody Integer idp, @RequestBody Integer idc,@RequestBody int quantite) {
		//transformationRessource Valide
		if(br.hasErrors()) {
			throw new SessionRessourceException();
		}
		
		//Session Valide
		Optional <Session> session = sessionService.findSession(idp, idc);
		if(session.isEmpty()) {
			throw new SessionRessourceException();	
		} 	
		
		//quantite de ressource suffissante
		SessionRessource sessionRessource =gestionRessourceservice.getSessionRessource(session.get(), transformationRessource.getRessourceLost());
		if(!gestionRessourceservice.verificationQuantiteRessource(sessionRessource, quantite)) {
			throw new SessionRessourceException();
		}
		
		//Batiment de Transformation possédé
		if(!gestionBatimentservice.verificationPossessionbatiment(session.get(), transformationRessource.getTransformation())) {
			throw new SessionRessourceException();
		}
		
		gestionRessourceservice.transformationRessource(session.get(), transformationRessource, quantite);
	}
}

