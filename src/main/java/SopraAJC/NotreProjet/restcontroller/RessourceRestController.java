package SopraAJC.NotreProjet.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import SopraAJC.NotreProjet.exceptions.RessourceException;
import SopraAJC.NotreProjet.models.JsonViews;
import SopraAJC.NotreProjet.models.Ressource;
import SopraAJC.NotreProjet.repositories.RessourceRepository;

@RestController
@RequestMapping("/api/ressource")
public class RessourceRestController {

	@Autowired
	RessourceRepository ressourceRepo;
	
	@GetMapping("")
	@JsonView(JsonViews.Common.class)
	public List<Ressource> findAll(){
		return ressourceRepo.findAll();
	}
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public Ressource findById(@PathVariable Integer id) {
		Optional<Ressource> opt = ressourceRepo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		
		throw new RessourceException();
	}
	
	
	@PostMapping("")
	@JsonView(JsonViews.Common.class)
	@ResponseStatus(HttpStatus.CREATED)
	public Ressource create(@RequestBody Ressource ressource) {
		return ressourceRepo.save(ressource);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		Optional<Ressource> opt =ressourceRepo.findById(id);
		if(opt.isPresent()) {
			ressourceRepo.deleteById(id);
		}
		else {
		throw new RessourceException();	
		}
	}
	
	@PutMapping("/{id}")
	@JsonView(JsonViews.Common.class)
	public Ressource replace(@RequestBody Ressource ressource, @PathVariable Integer id) {
		Optional<Ressource> opt =ressourceRepo.findById(id);
		if(opt.isPresent()) {
			ressource.setId(id);
			return ressourceRepo.save(ressource);
		}
		throw new RessourceException();
	}
	
}
