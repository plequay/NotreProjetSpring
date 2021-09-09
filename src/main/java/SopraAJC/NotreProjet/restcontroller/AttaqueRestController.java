package SopraAJC.NotreProjet.restcontroller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import SopraAJC.NotreProjet.exceptions.BatimentException;
import SopraAJC.NotreProjet.models.Attaque;
import SopraAJC.NotreProjet.models.CoutBatiment;
import SopraAJC.NotreProjet.models.CoutBatimentKey;
import SopraAJC.NotreProjet.models.CoutBatimentDto;
import SopraAJC.NotreProjet.models.JsonViews;
import SopraAJC.NotreProjet.repositories.AttaqueRepository;
import SopraAJC.NotreProjet.repositories.CoutBatimentRepository;
import SopraAJC.NotreProjet.repositories.RessourceRepository;

@RestController
@RequestMapping("/api/attaque")
public class AttaqueRestController {

	@Autowired
	private AttaqueRepository aRepo;
	
	@Autowired
	private CoutBatimentRepository cbRepo;
	
	@Autowired
	private RessourceRepository rRepo;
	
	@GetMapping("")
	@JsonView(JsonViews.BatimentWithCout.class)
	public List<Attaque> getAllBatimentAttaque() {
		return aRepo.findAll();
	}
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.BatimentWithCout.class)
	public Attaque findBatimentAttaqueById(@PathVariable Integer id) {
		Optional<Attaque> opt = aRepo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new BatimentException();
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	@JsonView(JsonViews.BatimentWithCout.class)
	public Attaque createBatimentAttaque(@Valid @RequestBody Attaque attaque, BindingResult br, @RequestBody List<CoutBatimentDto> list) {
		if (br.hasErrors()) {
			throw new BatimentException();
		}
		aRepo.save(attaque);
		list.stream().forEach(cbd -> {
			CoutBatiment cb = new CoutBatiment(new CoutBatimentKey(attaque,rRepo.findById(cbd.getIdRessource()).get()),cbd.getCout());
			cbRepo.save(cb);
		});
		return attaque;
	}
	
	@PutMapping("/{id}")
	@JsonView(JsonViews.BatimentWithCout.class)
	public Attaque replaceBatimentAttaque(@Valid @RequestBody Attaque attaque, BindingResult br, @RequestBody List<CoutBatimentDto> list, @PathVariable Integer id) {
		if(br.hasErrors()) {
			throw new BatimentException();
		}
		Optional<Attaque> opt = aRepo.findById(id);
		if(opt.isPresent()) {
			attaque.setId(id);
			for(CoutBatiment cb : opt.get().getCoutBatiment()) {
				cbRepo.delete(cb);
			}
			list.stream().forEach(cbd -> {
				CoutBatiment cb = new CoutBatiment(new CoutBatimentKey(attaque,rRepo.findById(cbd.getIdRessource()).get()),cbd.getCout());
				cbRepo.save(cb);
			});
			return aRepo.save(attaque);
		}
		throw new BatimentException();
	}
	
	@PatchMapping("/{id}")
	@JsonView(JsonViews.BatimentWithCout.class)
	public Attaque modifyBatimentAttaque(@RequestBody Map<String,Object> fields, @PathVariable Integer id) {
		Optional<Attaque> opt = aRepo.findById(id);
		if(opt.isPresent()) {
			Attaque batAttEnBase = opt.get();
			fields.forEach((key,value) -> {
				Field field = ReflectionUtils.findField(Attaque.class, key);
				ReflectionUtils.makeAccessible(field);
				ReflectionUtils.setField(field, batAttEnBase, value);
			});
			return aRepo.save(batAttEnBase);
		}
		throw new BatimentException();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBatimentAttaque(@PathVariable Integer id) {
		Optional<Attaque> opt = aRepo.findById(id);
		if(opt.isPresent()) {
			Attaque batAttADelete = opt.get();
			List<CoutBatiment> cbAttADelete = batAttADelete.getCoutBatiment();
			for(CoutBatiment cb : cbAttADelete) {
				cbRepo.delete(cb);
			}
			aRepo.delete(batAttADelete);
		}
		throw new BatimentException();
	}
}
