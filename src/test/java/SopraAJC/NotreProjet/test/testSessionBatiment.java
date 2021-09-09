package SopraAJC.NotreProjet.test;

import java.util.List;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.models.SessionBatiment;
import SopraAJC.NotreProjet.config.AppConfig;
import SopraAJC.NotreProjet.repositories.CompteRepository;
import SopraAJC.NotreProjet.repositories.PartieRepository;
import SopraAJC.NotreProjet.repositories.SessionBatimentRepository;
import SopraAJC.NotreProjet.repositories.SessionRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class testSessionBatiment {

	@Autowired
	SessionBatimentRepository sessionBatimentRepository;
	
	@Autowired
	SessionRepository sessionRepository;
	
	@Autowired
	PartieRepository partieRepository;
	
	@Autowired
	CompteRepository compteRepository;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
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
