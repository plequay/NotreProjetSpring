package SopraAJC.NotreProjet.test;

import static org.junit.Assert.*;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import SopraAJC.NotreProjet.config.AppConfig;
import SopraAJC.NotreProjet.repositories.RessourceRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class TestRessource {

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
	public void findByNomTest() {
		assertEquals("Bois", resRepo.findByNom("Bois").get().getNom());
	}

}
