package SopraAJC.NotreProjet.test;

import static org.junit.Assert.*;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import SopraAJC.NotreProjet.config.AppConfig;
import SopraAJC.NotreProjet.models.Attaque;
import SopraAJC.NotreProjet.models.CoutBatiment;
import SopraAJC.NotreProjet.models.Ressource;
import SopraAJC.NotreProjet.repositories.BatimentRepository;
import SopraAJC.NotreProjet.repositories.CoutBatimentRepository;
import SopraAJC.NotreProjet.repositories.RessourceRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class TestCoutBatimentRepository {

	@Autowired
	private CoutBatimentRepository cbRepo;
	
	@Autowired
	private BatimentRepository batRepo;
	
	@Autowired
	private RessourceRepository resRepo;
	
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
	@Rollback(true)
	@Transactional
	public void insertTest() {
		Attaque bat = (Attaque) batRepo.findById(1).get();
		Ressource res = resRepo.findByNom("Bois").get();
		CoutBatiment cBat = new CoutBatiment(bat,res,3);
		cbRepo.save(cBat);
	}
	
	
	@Test
	public void findByBatimentTest() {
		Attaque bat = (Attaque) batRepo.findById(1).get();
		cbRepo.findByBatiment(bat).stream().forEach(b ->{
			assertEquals("Bidule", b.getBatiment().getNom());
		});
	}
	
	@Test
	public void findByBatimentAndRessourceTest() {
		Attaque bat = (Attaque) batRepo.findById(1).get();
		Ressource res = resRepo.findByNom("Bois").get();
		cbRepo.findByBatimentAndRessource(bat,res).stream().forEach(b ->{
			assertEquals("Bidule", b.getBatiment().getNom());
			assertEquals(3, b.getCout());
			assertEquals("Bois", b.getRessource().getNom());
		});
	}

}
