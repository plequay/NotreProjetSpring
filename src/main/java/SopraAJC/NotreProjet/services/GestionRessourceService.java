package SopraAJC.NotreProjet.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SopraAJC.NotreProjet.models.Production;
import SopraAJC.NotreProjet.models.Ressource;
import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.models.SessionBatiment;
import SopraAJC.NotreProjet.models.SessionRessource;
import SopraAJC.NotreProjet.models.SessionRessourceKey;
import SopraAJC.NotreProjet.repositories.CoutBatimentRepository;
import SopraAJC.NotreProjet.repositories.RessourceRepository;
import SopraAJC.NotreProjet.repositories.SessionBatimentRepository;
import SopraAJC.NotreProjet.repositories.SessionRessourceRepository;
import SopraAJC.NotreProjet.models.Transformation;
import SopraAJC.NotreProjet.models.TransformationRessource;
import SopraAJC.NotreProjet.repositories.TransformationRessourceRepository;

@Service
public class GestionRessourceService {

	@Autowired
	private SessionRessourceRepository sessionRessourceRepository;
	
	@Autowired
	private RessourceRepository ressourceRepository;
	
	@Autowired
	private SessionBatimentRepository sessionBatimentRepository;
	
	@Autowired
	private TransformationRessourceRepository transformationRessourceRepository;
	
	@Autowired
	private CoutBatimentRepository coutBatimentRepository;
	
	
	/*
	    * Piocher ressources
	    * */
	public String piocherRessource(Session session) {
		
		int nbois = 0;
		int npierre = 0;
		int nminerais = 0;
		
		for(int i=0; i<10; i++)
		{
			int d = (int)Math.round(Math.random()*2);
			switch(d)
			{
				case 0 : nbois++; break;
				case 1 : npierre++;  break;
				case 2 : nminerais++;  break;
			}
		}
		
		Ressource rbois = ressourceRepository.findByNom("bois").get();
		SessionRessourceKey srk = new SessionRessourceKey(session, rbois);
		SessionRessource sessionRessource = sessionRessourceRepository.findById(srk).get();
		sessionRessource.setQuantite(sessionRessource.getQuantite()+nbois);
		sessionRessourceRepository.save(sessionRessource);
		
		Ressource rpierre = ressourceRepository.findByNom("pierre").get();
		srk = new SessionRessourceKey(session, rpierre);
		sessionRessource = sessionRessourceRepository.findById(srk).get();
		sessionRessource.setQuantite(sessionRessource.getQuantite()+npierre);
		sessionRessourceRepository.save(sessionRessource);
		
		Ressource rminerais = ressourceRepository.findByNom("minerais").get();
		srk = new SessionRessourceKey(session, rminerais);
		sessionRessource = sessionRessourceRepository.findById(srk).get();
		sessionRessource.setQuantite(sessionRessource.getQuantite()+nminerais);
		sessionRessourceRepository.save(sessionRessource);
		
		return "\nVous avez pioché " + nbois + " bois, " + npierre + " pierre(s), " + nminerais + " minerais !";
	}
	
	/*
	    * Gain ressources batiment de production
	    * */
	public List<String> productionRessource(Session session) {
		List<String> message = new ArrayList<String>();
		
		//recupération  de la liste des batiment de production pour la session
		List<SessionBatiment> listSessionBatimentProduction = sessionBatimentRepository.findBySessionAndBatimentProduction(session);
		
		if(listSessionBatimentProduction.isEmpty()) {
			message.add("Vous ne produisez pas de ressource");
		}
		else {
			//boucle pour faire la production de chaque batiment
			for(SessionBatiment sessionBatiment : listSessionBatimentProduction) {
				////recup informations de production
				Production batimentProd= (Production)sessionBatiment.getBatiment();
				//ressource produite
				Ressource ressource = batimentProd.getRessource();
				//quantite produite
				int quantite = batimentProd.getQuantiteProduite();
				
				////recup la sessionRessource à maj et maj
				SessionRessourceKey srk = new SessionRessourceKey(session, ressource);
				SessionRessource sessionRessource = sessionRessourceRepository.findById(srk).get();
				sessionRessource.setQuantite(sessionRessource.getQuantite()+quantite);				
				
				sessionRessourceRepository.save(sessionRessource);
				
				message.add("Votre "+ batimentProd+" produit "+ quantite + " de " + ressource +" !");
			}
		}
		return message;
	}
	
	/*
	    * Liste des batiment de Production de la session
	    * */	
	public List<SessionBatiment> listeBatimentProduction(Session session){
		List<SessionBatiment> listBatimentProduction = sessionBatimentRepository.findBySessionAndBatimentProduction(session);
		return listBatimentProduction;
	}
	
	/*
	    * Liste des batiment de Transformation de la session
	    * */	
	public List<SessionBatiment> listeBatimentTransformation(Session session){
		List<SessionBatiment> listBatimentTransformation = sessionBatimentRepository.findBySessionAndBatimentTransformation(session);
		return listBatimentTransformation;
	}
	
	/*
	    * Liste des TransformationRessource pour le batiment transformation
	    * */	
	public List<TransformationRessource> listeTransformationRessource(Transformation transformation){
		List<TransformationRessource> listTransformationRessource = transformationRessourceRepository.findByTransformation(transformation);
		return listTransformationRessource;
	}
	
	/*
	    * TransformationRessource par son id
	    * */	
	public TransformationRessource getTransformationRessourceById(Integer id){
		TransformationRessource transformationRessource = transformationRessourceRepository.findById(id).get();
		return transformationRessource;
	}
	
	
	/*
	    * Transformer ressources
	    * */
	public void transformationRessource(Session session, TransformationRessource transformationRessource,int quantite) {
		
		//Diminue la ressource transformé
		SessionRessourceKey srk = new SessionRessourceKey(session, transformationRessource.getRessourceLost());
		SessionRessource sessionRessource = sessionRessourceRepository.findById(srk).get();
		sessionRessource.setQuantite(sessionRessource.getQuantite()- quantite);
		sessionRessourceRepository.save(sessionRessource);
		
		// augmente la ressource produite
		srk = new SessionRessourceKey(session, transformationRessource.getRessourceWin());
		sessionRessource = sessionRessourceRepository.findById(srk).get();
		sessionRessource.setQuantite(sessionRessource.getQuantite()+ quantite);
		sessionRessourceRepository.save(sessionRessource);		
	}
	
	/*
	    * Verification que la ressource à consommé est suffissante
	    * */
	public boolean verificationQuantiteRessource (SessionRessource sessionRessource, int quantite) {
		return sessionRessource.getQuantite()>=quantite;
	}
	
	/*
	    * récupération d'une sessionRessource à partir de la session et la ressource
	    * */
	public SessionRessource getSessionRessource(Session session, Ressource ressource) {
		return sessionRessourceRepository.findById(new SessionRessourceKey(session,ressource)).get();
	}
	
	public List<SessionRessource> getSessionRessourceBySession(Session session){
		return sessionRessourceRepository.findBySession(session);
	}
	
	/*
	    * Verification que la ressource n'est pas lié à un cout batiment ou transformation ressource pour permettre la suppression
	    * */
	public boolean independance(Ressource ressource) {
		if(transformationRessourceRepository.findByRessourceLost(ressource).isEmpty() &&
		transformationRessourceRepository.findByRessourceWin(ressource).isEmpty() && 
		coutBatimentRepository.findAllCoutByRessource(ressource).isEmpty()) {
			return true;
		}
		return false;
	}
}

