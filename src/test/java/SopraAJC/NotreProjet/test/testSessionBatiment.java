package SopraAJC.NotreProjet.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.models.SessionBatiment;
import SopraAJC.NotreProjet.repositories.CompteRepository;
import SopraAJC.NotreProjet.repositories.PartieRepository;
import SopraAJC.NotreProjet.repositories.SessionBatimentRepository;
import SopraAJC.NotreProjet.repositories.SessionRepository;

@SpringBootTest
public class testSessionBatiment {

	@Autowired
	SessionBatimentRepository sessionBatimentRepository;
	
	@Autowired
	SessionRepository sessionRepository;
	
	@Autowired
	PartieRepository partieRepository;
	
	@Autowired
	CompteRepository compteRepository;
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	public void setUp() throws Exception {
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindBySessionAndBatimentProduction() {
//		Partie partie = partieRepository.findById(1).get();
//		Compte compte = compteRepository.findById(1).get();
//		Session session = sessionRepository.findById(new SessionKey(partie,compte)).get();
		
		List<Session> sessions = sessionRepository.findAll();
		System.out.println(sessions);
		
		Session session = sessions.get(3);
		
		List<SessionBatiment> listDef = sessionBatimentRepository.findBySessionAndBatimentProduction(session);
		
		for (SessionBatiment sessionBatiment : listDef) {
			System.out.println(sessionBatiment.getBatiment().getNom());
		}
	}
	


}
