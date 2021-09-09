package SopraAJC.NotreProjet.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import SopraAJC.NotreProjet.models.SessionBatiment;
import SopraAJC.NotreProjet.services.AttaqueService;

@RestController
@RequestMapping("/api/attaque")
public class AttaqueRestController {

	@Autowired
	AttaqueService attService;

	@PatchMapping("/allattackone")
	public SessionBatiment allAttackOne() {

	}
	
	@PatchMapping("/allattackall")
	public List<SessionBatiment> allAttackAll() {

	}
	@PatchMapping("/oneattackall")
	public List<SessionBatiment> oneAttackAll() {

	}
	@PatchMapping("/oneattackone")
	public SessionBatiment oneAttackOne() {

	}

}
