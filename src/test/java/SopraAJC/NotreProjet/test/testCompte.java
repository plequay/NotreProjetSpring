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
import SopraAJC.NotreProjet.models.Joueur;
import SopraAJC.NotreProjet.repositories.CompteRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class testCompte {

	@Autowired
	private CompteRepository cptRepo;
	
	
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
	@Rollback(false)
	@Transactional
	public void insertTest() {
		Joueur j = new Joueur("A","A","A","A","A");
		cptRepo.save(j);
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void findBySurnom() {
		assertEquals("A", cptRepo.findBySurnom("A").get().getSurnom());
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void findByLoginAndPassword() {
		Joueur j = new Joueur("J","J","J","J","J");
		cptRepo.save(j);
		assertEquals(j, cptRepo.findByLoginAndPassword(j.getLogin(),j.getPassword()).get());
	}

}
