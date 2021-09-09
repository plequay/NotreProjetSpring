package SopraAJC.NotreProjet.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import SopraAJC.NotreProjet.models.CoutBatiment;
import SopraAJC.NotreProjet.models.Defense;
import SopraAJC.NotreProjet.repositories.BatimentRepository;
import SopraAJC.NotreProjet.repositories.SessionBatimentRepository;
import SopraAJC.NotreProjet.repositories.SessionRepository;


@SpringBootTest
public class TestSessionBatimentRepo {
	
	@Autowired
	SessionBatimentRepository sessionBatimentRepository;
	
	@Autowired
	SessionRepository sessionRepository;
	
	@Autowired
	BatimentRepository batimentRepository;

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
