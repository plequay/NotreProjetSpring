package SopraAJC.NotreProjet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import SopraAJC.NotreProjet.config.AppConfig;
import SopraAJC.NotreProjet.models.Attaque;
import SopraAJC.NotreProjet.models.CoutBatiment;
import SopraAJC.NotreProjet.models.Ressource;
import SopraAJC.NotreProjet.repositories.BatimentRepository;
import SopraAJC.NotreProjet.repositories.CoutBatimentRepository;
import SopraAJC.NotreProjet.repositories.RessourceRepository;

@SpringBootTest
public class TestCoutBatimentRepository {

	@Autowired
	private CoutBatimentRepository cbRepo;
	
	@Autowired
	private BatimentRepository batRepo;
	
	@Autowired
	private RessourceRepository resRepo;
	
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
//	@Rollback(true)
//	@Transactional
//	public void insertTest() {
//		Attaque bat = (Attaque) batRepo.findById(1).get();
//		Ressource res = resRepo.findByNom("Bois").get();
//		CoutBatiment cBat = new CoutBatiment(bat,res,3);
//		cbRepo.save(cBat);
//	}
//
//
//	@Test
//	public void findByBatimentTest() {
//		Attaque bat = (Attaque) batRepo.findById(1).get();
//		cbRepo.findByBatiment(bat).stream().forEach(b ->{
//			assertEquals("Bidule", b.getBatiment().getNom());
//		});
//	}
//
//	@Test
//	public void findByBatimentAndRessourceTest() {
//		Attaque bat = (Attaque) batRepo.findById(1).get();
//		Ressource res = resRepo.findByNom("Bois").get();
//		cbRepo.findByBatimentAndRessource(bat,res).stream().forEach(b ->{
//			assertEquals("Bidule", b.getBatiment().getNom());
//			assertEquals(3, b.getCout());
//			assertEquals("Bois", b.getRessource().getNom());
//		});
//	}

}
