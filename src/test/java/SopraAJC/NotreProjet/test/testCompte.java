package SopraAJC.NotreProjet.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import SopraAJC.NotreProjet.models.Joueur;
import SopraAJC.NotreProjet.repositories.CompteRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class testCompte {

	@Autowired
	private CompteRepository cptRepo;

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
	@Rollback(false)
	@Transactional
	public void insertTest() {
		Joueur j = new Joueur("A","A","A","A");
		cptRepo.save(j);
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void findByUsername() {
		assertEquals("A", cptRepo.findByUsername("A").get().getUsername());
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void findByUsernameAndPassword() {
		Joueur j = new Joueur("J","J","J","J");
		cptRepo.save(j);
		assertEquals(j, cptRepo.findByUsernameAndPassword(j.getUsername(),j.getPassword()).get());
	}

}
