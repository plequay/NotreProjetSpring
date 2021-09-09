package SopraAJC.NotreProjet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import SopraAJC.NotreProjet.models.Ressource;
import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.models.SessionBatiment;
import SopraAJC.NotreProjet.models.SessionRessource;
import SopraAJC.NotreProjet.models.SessionRessourceKey;
import SopraAJC.NotreProjet.repositories.RessourceRepository;
import SopraAJC.NotreProjet.repositories.SessionBatimentRepository;
import SopraAJC.NotreProjet.repositories.SessionRessourceRepository;

public class GestionRessourceService {

	@Autowired
	SessionRessourceRepository sessionRessourceRepository;
	
	@Autowired
	RessourceRepository ressourceRepository;
	
	@Autowired
	SessionBatimentRepository sessionBatimentRepository;
	 
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
		
		Ressource rpierre = ressourceRepository.findByNom("pierre").get();
		srk = new SessionRessourceKey(session, rpierre);
		sessionRessource = sessionRessourceRepository.findById(srk).get();
		sessionRessource.setQuantite(sessionRessource.getQuantite()+npierre);
		
		Ressource rminerais = ressourceRepository.findByNom("bois").get();
		srk = new SessionRessourceKey(session, rminerais);
		sessionRessource = sessionRessourceRepository.findById(srk).get();
		sessionRessource.setQuantite(sessionRessource.getQuantite()+nminerais);
		
		
		return "\nVous avez pioch� " + nbois + " bois, " + npierre + " pierre(s), " + nminerais + " minerais !";
	}
	
	/*
	    * Gain ressources batiment de production
	    * */
	public String productionRessource(Session session) {
		int nbois = 0;
		int npierre = 0;
		int nminerais = 0;
		
		List<SessionBatiment> listSessionBatimentProduction = sessionBatimentRepository.findBySessionAndBatimentProduction(session);
		
		if(listSessionBatimentProduction.isEmpty()) {
			return "Vous ne produisez pas de ressource";
		}
		
		for(SessionBatiment sessionBatiment : listSessionBatimentProduction) {
			//sessionRessourceRepository.findById(null)
		}
		

		return "message";
	}
	
//	for (Batiment b : this.constructions)
//	{
//		if(b instanceof Carriere)
//		{
//			pierre+=5;
//			System.out.println("\nVotre carriere vous a rapporte 5 pierres supplementaires ("+pierre+" pierre(s) au total !)\n");	
//		}
//		else if(b instanceof Carrier)
//		{
//			pierre+=2;
//			System.out.println("\nVotre carrier vous a rapporte 2 pierres supplementaires ("+pierre+" pierre(s) au total !)\n");	
//		}
//		else if (b instanceof Mine)
//		{
//			minerais+=5;
//			System.out.println("\nVotre mine vous a rapporte 5 minerais supplementaires ("+minerais+" minerais au total !)\n");	
//		}
//		else if (b instanceof Mineur)
//		{
//			minerais+=2;
//			System.out.println("\nVotre mineur vous a rapporte 2 minerais supplementaires ("+minerais+" minerais au total !)\n");	
//		}
//		else if (b instanceof Scierie)
//		{
//			bois+=5;
//			System.out.println("\nVotre scierie vous a rapporte 5 bois supplementaires ("+bois+" bois au total !)\n");	
//		}
//		else if (b instanceof Bucheron)
//		{
//			bois+=2;
//			System.out.println("\nVotre bucheron vous a rapporte 2 minerais supplementaires ("+minerais+" minerais au total !)\n");	
//		}
//	}
//	
//	for (Ressource r : this.ressources)	//modification du stock de ressources du joueur en fonction du cout (cf. methode actuAchat de la classe ressources)
//	{
//		if(r instanceof Bois)
//		{
//			r.actuGain(bois);
//		}
//		else if(r instanceof Pierre) {
//			r.actuGain(pierre);
//		}
//		else if(r instanceof Minerais) {
//			r.actuGain(minerais);
//		}
//
//	}
	
	
	/*
	    * Transformer ressources
	    * */
	
	
}
