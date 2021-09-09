package SopraAJC.NotreProjet.services;

import SopraAJC.NotreProjet.models.*;
import SopraAJC.NotreProjet.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ConsoleService implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    TransformationRessourceRepository transformationRessourceRepository;

    @Override
    public void run(String... args) throws Exception {

        LOGGER.info("Hey guys -> Spring Boot Application started");

       initBDD();
       //initRessource();
       //initBatiment();
        

    }

    public void initBDD() {

        Admin admin1 = new Admin("admin1", passwordEncoder.encode("admin1"), "admin1", "admin1");
        Joueur joueur1 = new Joueur("joueur1", passwordEncoder.encode("joueur1"), "joueur1", "joueur1");
        Joueur joueur2 = new Joueur("joueur2", passwordEncoder.encode("joueur2"), "joueur2", "joueur2");
        Joueur joueur3 = new Joueur("joueur3", passwordEncoder.encode("joueur3"), "joueur3", "joueur3");
        Joueur joueur4 = new Joueur("joueur4", passwordEncoder.encode("joueur4"), "joueur4", "joueur4");

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

    public void initRessource() {
    	Ressource bois = new Ressource("bois");
        Ressource pierre = new Ressource("pierre");
        Ressource minerais = new Ressource("minerais");
        Ressource charbon = new Ressource("charbon");
        Ressource fer = new Ressource("fer");
        Ressource gold = new Ressource("gold");
        Ressource cuivre = new Ressource("cuivre");

        ressourceRepository.save(bois);
        ressourceRepository.save(pierre);
        ressourceRepository.save(minerais);
        ressourceRepository.save(charbon);
        ressourceRepository.save(fer);
        ressourceRepository.save(gold);
        ressourceRepository.save(cuivre);    	
    }
    
    public void initBatiment() {
    	// Merveille(true)
    	Ressource bois = ressourceRepository.findByNom("bois").get();
    	Ressource pierre = ressourceRepository.findByNom("pierre").get();
    	Ressource minerais = ressourceRepository.findByNom("minerais").get();
    	Ressource charbon = ressourceRepository.findByNom("charbon").get();
    	Ressource fer = ressourceRepository.findByNom("fer").get();
    	Ressource gold = ressourceRepository.findByNom("gold").get();
    	Ressource cuivre = ressourceRepository.findByNom("cuivre").get();
    	
    	////Defense
    	//Bastide
    	Defense bastide = new Defense("Bastide", 50, 1, true);
    	batimentRepository.save(bastide);
    	
    	//Muraille
    	Defense Muraille = new Defense("Muraille", 50, 1, true);
    	batimentRepository.save(Muraille);
    	
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Muraille, bois), 3));
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Muraille, pierre), 3));
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Muraille, fer), 3));
    	
    	//Forteresse
    	Defense Forteresse = new Defense("Forteresse", 200, 1, true);
    	batimentRepository.save(Forteresse);
    	
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Muraille, pierre), 10));
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Muraille, fer), 10));
    	
    	
    	////Batiment transformation
    	//Four
    	Transformation four = new Transformation("Four", 30, 1, true);
    	batimentRepository.save(four);
    	
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(four, bois), 3));
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(four, pierre), 3));
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(four, minerais), 3));
    	

    	transformationRessourceRepository.save(new TransformationRessource(four, bois, charbon));
    	
    	//Fonderie
    	Transformation fonderie = new Transformation("Fonderie", 40, 1, true);
    	batimentRepository.save(fonderie);
    	
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(four, pierre), 3));
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(four, charbon), 6));
    	
    	transformationRessourceRepository.save(new TransformationRessource(fonderie, minerais, fer));
    	transformationRessourceRepository.save(new TransformationRessource(fonderie, minerais, gold));
    	transformationRessourceRepository.save(new TransformationRessource(fonderie, minerais, cuivre));
    	
    	
    	////Production
    	//Bucheron
    	Production bucheron = new Production("Bucheron", 15, 1, false, bois, 1);
    	batimentRepository.save(bucheron);
    	
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(bucheron, pierre), 2));
    	
    	//Mineur
    	Production mineur = new Production("Mineur", 10, 1, false, minerais, 1);
    	batimentRepository.save(mineur);
    	
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(mineur, bois), 2));
    	
    	//Bucheron
    	Production carrier = new Production("Carrier", 10, 1, false, pierre, 1);
    	batimentRepository.save(carrier);
    	
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(carrier, bois), 2));
    	
    	//Scierie
    	Production scierie = new Production("Scierie", 10, 1, true, bois, 1);
    	batimentRepository.save(scierie);
    	
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(scierie, pierre), 8));
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(scierie, minerais), 8));
    	
    	//Carriere
    	Production Carriere = new Production("Carriere", 30, 1, true, pierre, 5);
    	batimentRepository.save(Carriere);
    	
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Carriere, bois), 8));
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Carriere, minerais), 8));
    	
    	//Mine
    	Production Mine = new Production("Mine", 30, 1, true, bois, 5);
    	batimentRepository.save(Mine);
    	
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Mine, pierre), 8));
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Mine, bois), 8));
    	
    	
    	////Attaque
    	//Catapulte
    	Attaque Catapulte = new Attaque("Catapulte", 15, 25.0, 1, true);
    	batimentRepository.save(Catapulte);
    	
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Catapulte, pierre), 3));
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Catapulte, bois), 5));
    	
    	//Scorpion
    	Attaque Scorpion = new Attaque("Scorpion", 30, 75.0, 1, true);
    	batimentRepository.save(Scorpion);
    	
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Scorpion, pierre), 4));
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Scorpion, bois), 8));
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Scorpion, fer), 4)); 
         
    	//Baliste
    	Attaque Baliste = new Attaque("Baliste", 120, 240, 1, true);
    	batimentRepository.save(Baliste);
    	
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Baliste, pierre), 15));
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Baliste, bois), 10));
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Baliste, fer), 8)); 
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Baliste, cuivre), 4));
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Baliste, gold), 4));
        
    	
    	////Merveille
    	Defense Merveille = new Defense("Merveille", 25, 1, true);
    	batimentRepository.save(Merveille);
    	
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Merveille, bois), 2));
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Merveille, pierre), 2));
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Merveille, minerais), 2));
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Merveille, charbon), 2));
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Merveille, cuivre), 2));
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Merveille, fer), 2));
    	coutBatimentRepository.save(new CoutBatiment(new CoutBatimentKey(Merveille, gold), 2));
    	
         
    }
}
