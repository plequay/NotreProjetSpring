package SopraAJC.NotreProjet.test;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import SopraAJC.NotreProjet.config.AppConfig;
import SopraAJC.NotreProjet.models.CoutBatiment;
import SopraAJC.NotreProjet.models.Defense;
import SopraAJC.NotreProjet.repositories.BatimentRepository;
import SopraAJC.NotreProjet.repositories.SessionBatimentRepository;
import SopraAJC.NotreProjet.repositories.SessionRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class TestSessionBatimentRepo {
	
	@Autowired
	SessionBatimentRepository sessionBatimentRepository;
	
	@Autowired
	SessionRepository sessionRepository;
	
	@Autowired
	BatimentRepository batimentRepository;

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
	
//	@Test
//	public void findBatimentBySession() {
//		Optional<Session> opt= sr.findById(2);
//		System.out.println();
//		List<SessionBatiment> ses = sbr.findBySession(opt.get());
//		System.out.println(ses);
//	}
	
	@Test
	public void testFindByIdWithCoutBatiment() {

		Defense mur = (Defense) batimentRepository.findByNom("mur").get();
		
		System.out.println(mur.getNom());
		System.out.println(mur);
		
		mur = (Defense) batimentRepository.findByIdWithCoutBatiment(mur.getId()).get();
			
		
		System.out.println(mur.getNom());
		
		for (CoutBatiment coutBatiment : mur.getCoutBatiment()) {
			System.out.println(coutBatiment.getId().getRessource().getNom());
			System.out.println(coutBatiment.getCout());
		}
		
	}

}
