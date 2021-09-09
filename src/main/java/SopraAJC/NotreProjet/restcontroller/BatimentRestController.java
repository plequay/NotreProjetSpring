package SopraAJC.NotreProjet.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import SopraAJC.NotreProjet.models.JsonViews;
import SopraAJC.NotreProjet.repositories.BatimentRepository;

@RestController
@RequestMapping("/api/batiment")
public class BatimentRestController {

	@Autowired
	private BatimentRepository bRepo;
	
	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public void getAllBySession() {
		
	}
}
