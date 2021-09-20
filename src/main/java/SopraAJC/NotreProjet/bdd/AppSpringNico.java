package SopraAJC.NotreProjet.bdd;

import SopraAJC.NotreProjet.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import SopraAJC.NotreProjet.models.Admin;
import SopraAJC.NotreProjet.models.Attaque;
import SopraAJC.NotreProjet.models.CoutBatiment;
import SopraAJC.NotreProjet.models.CoutBatimentKey;
import SopraAJC.NotreProjet.models.Defense;
import SopraAJC.NotreProjet.models.Joueur;
import SopraAJC.NotreProjet.models.Partie;
import SopraAJC.NotreProjet.models.Production;
import SopraAJC.NotreProjet.models.Ressource;
import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.models.SessionBatiment;
import SopraAJC.NotreProjet.models.SessionKey;
import org.springframework.security.crypto.password.PasswordEncoder;
import SopraAJC.NotreProjet.repositories.BatimentRepository;
import SopraAJC.NotreProjet.repositories.CompteRepository;
import SopraAJC.NotreProjet.repositories.CoutBatimentRepository;
import SopraAJC.NotreProjet.repositories.PartieRepository;
import SopraAJC.NotreProjet.repositories.RessourceRepository;
import SopraAJC.NotreProjet.repositories.SessionBatimentRepository;
import SopraAJC.NotreProjet.repositories.SessionRepository;
import SopraAJC.NotreProjet.repositories.SessionRessourceRepository;

public class AppSpringNico {

	@Autowired
	CompteRepository compteRepository;
	
	@Autowired
	SessionRessourceRepository sessionRessourceRepo;
	
	@Autowired
	PartieRepository partieRepository;
	
	@Autowired
	SessionRepository sessionRepository;
	
	@Autowired
	SessionBatimentRepository sessionBatimentRepository;
	
	@Autowired
	BatimentRepository batimentRepository;
	
	@Autowired
	CoutBatimentRepository coutBatimentRepository;
	
	@Autowired
	RessourceRepository ressourceRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public  void run(String[] args) {

		Admin admin1 = new Admin("admin1", passwordEncoder.encode("admin1"),  "admin1", "admin1");
		Joueur joueur1 = new Joueur( "joueur1", passwordEncoder.encode("joueur1"), "joueur1", "joueur1");
		Joueur joueur2 = new Joueur( "joueur2",passwordEncoder.encode("joueur2"), "joueur2", "joueur2");
		Joueur joueur3 = new Joueur( "joueur3",passwordEncoder.encode("joueur3"), "joueur3", "joueur3");
		Joueur joueur4 = new Joueur( "joueur4",passwordEncoder.encode("joueur4"), "joueur4", "joueur4");

		compteRepository.save(admin1);
		compteRepository.save(joueur1);
		compteRepository.save(joueur2);
		compteRepository.save(joueur3);
		compteRepository.save(joueur4);
		
		Partie partie = new Partie();
		partieRepository.save(partie);
		
		Session session1 = new Session(new SessionKey(partie, joueur1));
		Session session2 = new Session(new SessionKey(partie, joueur2));
		Session session3 = new Session(new SessionKey(partie, joueur3));
		Session session4 = new Session(new SessionKey(partie, joueur4));
		
		sessionRepository.save(session1);
		sessionRepository.save(session2);
		sessionRepository.save(session3);
		sessionRepository.save(session4);
		
		
		Ressource bois = new Ressource("bois");
		Ressource pierre = new Ressource("pierre");
		Ressource minerais = new Ressource("minerais");
		
		ressourceRepository.save(bois);
		ressourceRepository.save(pierre);
		ressourceRepository.save(minerais);
		
		
		Defense bastide = new Defense("Bastide", 5.0, 1, true);
		Defense mur = new Defense("Mur", 10.0, 1, true);
		Defense toit = new Defense("Toit", 30.0, 1, true);
		
		Attaque fusil = new Attaque("Fusil", 5.0, 50.0, 1, true);
		Attaque machette = new Attaque("Machette", 10.0, 100.0, 1, true);
		Attaque lanceRoquette = new Attaque("lanceRoquette", 30.0, 150.0, 1, true);
		
		Production mine = new Production("mine", 5.0, 1, true, 5);
		Production carriere = new Production("carriere", 10.0, 1, true, 5);
		Production usine = new Production("usine", 30.0, 1, true, 5);
				
		batimentRepository.save(bastide);
		batimentRepository.save(mur);
		batimentRepository.save(toit);
		batimentRepository.save(fusil);
		batimentRepository.save(machette);
		batimentRepository.save(lanceRoquette);
		batimentRepository.save(mine);
		batimentRepository.save(carriere);
		batimentRepository.save(usine);
		
		
		
		coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(mur, bois), 5));
		coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(mur, minerais), 2));
		coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(mur, pierre), 7));
		
		coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(fusil, minerais), 10));
		
		coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(carriere, bois), 2));
		

				
		
		SessionBatiment sessionBatiment1 = new SessionBatiment(session1, bastide, bastide.getPointsDefense());
		sessionBatimentRepository.save(sessionBatiment1);
		
		SessionBatiment sessionBatiment2 = new SessionBatiment(session2, bastide, bastide.getPointsDefense());
		sessionBatimentRepository.save(sessionBatiment2);
		
		SessionBatiment sessionBatiment3 = new SessionBatiment(session1, lanceRoquette, lanceRoquette.getPointsDefense(), lanceRoquette.getPointsDAttaque());
		sessionBatimentRepository.save(sessionBatiment3);
		
		SessionBatiment sessionBatiment4 = new SessionBatiment(session2, machette, machette.getPointsDefense(), machette.getPointsDAttaque());
		sessionBatimentRepository.save(sessionBatiment4);
		
		SessionBatiment sessionBatiment5 = new SessionBatiment(session3, usine, usine.getPointsDefense());
		sessionBatimentRepository.save(sessionBatiment5);
		
		SessionBatiment sessionBatiment6 = new SessionBatiment(session4, carriere, carriere.getPointsDefense());
		sessionBatimentRepository.save(sessionBatiment6);
		
	}
//	public  void run(String[] args) {
//
//		Admin admin1 = new Admin("login", "password", "prenom",  "nom", "admin1");
//		Joueur joueur1 = new Joueur("login", "password", "prenom", "nom", "joueur1");
//		Joueur joueur2 = new Joueur("login", "password", "prenom", "nom", "joueur2");
//		Joueur joueur3 = new Joueur("login", "password", "prenom", "nom", "joueur3");
//		Joueur joueur4 = new Joueur("login", "password", "prenom", "nom", "joueur4");
//
//		compteRepository.save(admin1);
//		compteRepository.save(joueur1);
//		compteRepository.save(joueur2);
//		compteRepository.save(joueur3);
//		compteRepository.save(joueur4);
//		
//		Partie partie = new Partie();
//		partieRepository.save(partie);
//		
//		Session session1 = new Session(new SessionKey(partie, joueur1));
//		Session session2 = new Session(new SessionKey(partie, joueur2));
//		Session session3 = new Session(new SessionKey(partie, joueur3));
//		Session session4 = new Session(new SessionKey(partie, joueur4));
//		
//		sessionRepository.save(session1);
//		sessionRepository.save(session2);
//		sessionRepository.save(session3);
//		sessionRepository.save(session4);
//		
//		
//		Ressource bois = new Ressource("bois");
//		Ressource pierre = new Ressource("pierre");
//		Ressource minerais = new Ressource("minerais");
//		
//		ressourceRepository.save(bois);
//		ressourceRepository.save(pierre);
//		ressourceRepository.save(minerais);
//		
//		
//		Defense bastide = new Defense("Bastide", 5.0, 1, true);
//		Defense mur = new Defense("Mur", 10.0, 1, true);
//		Defense toit = new Defense("Toit", 30.0, 1, true);
//		
//		Attaque fusil = new Attaque("Fusil", 5.0, 50.0, 1, true);
//		Attaque machette = new Attaque("Machette", 10.0, 100.0, 1, true);
//		Attaque lanceRoquette = new Attaque("lanceRoquette", 30.0, 150.0, 1, true);
//		
//		Production mine = new Production("mine", 5.0, 1, true, 5);
//		Production carriere = new Production("carriere", 10.0, 1, true, 5);
//		Production usine = new Production("usine", 30.0, 1, true, 5);
//				
//		batimentRepository.save(bastide);
//		batimentRepository.save(mur);
//		batimentRepository.save(toit);
//		batimentRepository.save(fusil);
//		batimentRepository.save(machette);
//		batimentRepository.save(lanceRoquette);
//		batimentRepository.save(mine);
//		batimentRepository.save(carriere);
//		batimentRepository.save(usine);
//		
//		
//		
//		coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(mur, bois), 5));
//		coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(mur, minerais), 2));
//		coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(mur, pierre), 7));
//		
//		coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(fusil, minerais), 10));
//		
//		coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(carriere, bois), 2));
//		
//
//				
//		
//		SessionBatiment sessionBatiment1 = new SessionBatiment(session1, bastide, bastide.getPointsDefense());
//		sessionBatimentRepository.save(sessionBatiment1);
//		
//		SessionBatiment sessionBatiment2 = new SessionBatiment(session2, bastide, bastide.getPointsDefense());
//		sessionBatimentRepository.save(sessionBatiment2);
//		
//		SessionBatiment sessionBatiment3 = new SessionBatiment(session1, lanceRoquette, lanceRoquette.getPointsDefense(), lanceRoquette.getPointsDAttaque());
//		sessionBatimentRepository.save(sessionBatiment3);
//		
//		SessionBatiment sessionBatiment4 = new SessionBatiment(session2, machette, machette.getPointsDefense(), machette.getPointsDAttaque());
//		sessionBatimentRepository.save(sessionBatiment4);
//		
//		SessionBatiment sessionBatiment5 = new SessionBatiment(session3, usine, usine.getPointsDefense());
//		sessionBatimentRepository.save(sessionBatiment5);
//		
//		SessionBatiment sessionBatiment6 = new SessionBatiment(session4, carriere, carriere.getPointsDefense());
//		sessionBatimentRepository.save(sessionBatiment6);
//		
//	}
}
