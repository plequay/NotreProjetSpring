package SopraAJC.NotreProjet.services;

import SopraAJC.NotreProjet.models.*;
import SopraAJC.NotreProjet.repositories.*;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CreationPartieService {

    @Autowired
    PartieRepository partieRepository;

    @Autowired
    SessionRepository sessionRepository;
    
    @Autowired
    SessionRessourceRepository sessionRessourceRepository;
    
    @Autowired
    RessourceRepository ressourceRepository;

    @Autowired
    SessionBatimentRepository sessionBatimentRepository;
    
    @Autowired
    BatimentRepository	batimentRepository;
    
    /*
     * Créer une partie vide
     * */
    public Partie saveNewPartie(){

        Partie partie = new Partie();
        partieRepository.save(partie);

        return partie;
    }

    /*
     * Ajouter un joueur à une partie & initialise la session
     * */
    public void saveJoueurInAPartie(Joueur joueur, Partie partie){

        Session session = new Session(new SessionKey(partie, joueur));
        sessionRepository.save(session);

    }

    /*
    * Créer une partie avec des comptes deja existant & initialise les sessions
    * */
    public List<Session> saveNewPartieWithJoueurs(List<Joueur> joueurList){

        Partie partie = saveNewPartie();

        List<Session> sessionList = new ArrayList<Session>();

        for (Joueur joueur:joueurList) {
        	Session session = new Session(new SessionKey(partie, joueur));
            sessionList.add(session);
        }
        sessionRepository.saveAll(sessionList);
        
        return sessionList;
    }
    
    
    /*
    * initialisation list<sessionRessource> pour chaque session et la bastide
    * */
    
    public void initSessionRessource(List<Session> sessionList) {
    	for (Session session:sessionList) {
    		for(Ressource ressource:ressourceRepository.findAll()) {
	        	SessionRessource sessionRessource = new SessionRessource(new SessionRessourceKey(session, ressource), 0);
	        	sessionRessourceRepository.save(sessionRessource);
    		}
    		Batiment batiment = batimentRepository.findByNom("bastide").get();
    		SessionBatiment sessionBatiment = new SessionBatiment(session, batiment, batiment.getPointsDefense());
        }
    }
    
}


