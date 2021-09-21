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
import org.springframework.web.bind.annotation.CrossOrigin;
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
import SopraAJC.NotreProjet.models.CoutBatiment;
import SopraAJC.NotreProjet.models.CoutBatimentKey;
import SopraAJC.NotreProjet.models.CoutBatimentDto;
import SopraAJC.NotreProjet.models.Defense;
import SopraAJC.NotreProjet.models.JsonViews;
import SopraAJC.NotreProjet.repositories.CoutBatimentRepository;
import SopraAJC.NotreProjet.repositories.DefenseRepository;
import SopraAJC.NotreProjet.repositories.RessourceRepository;

@RestController
@RequestMapping("/api/defense")
@CrossOrigin(origins = "*")
public class DefenseRestController {

	@Autowired
	private DefenseRepository dRepo;
	
	@Autowired
	private CoutBatimentRepository cbRepo;
	
	@Autowired
	private RessourceRepository rRepo;
	
	@GetMapping("")
	@JsonView(JsonViews.BatimentWithCout.class)
	public List<Defense> getAllBatimentDefense() {
		return dRepo.findAll();
	}
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.BatimentWithCout.class)
	public Defense findBatimentDefenseById(@PathVariable Integer id) {
		Optional<Defense> opt = dRepo.findByIdWithCoutBatiment(id);
		System.out.println(opt.get().getCoutBatiment());
		if(opt.isPresent()) {
			return opt.get();
		}
		else {
		throw new BatimentException();
		}
	}
	
//	@PostMapping("")
//	@ResponseStatus(HttpStatus.CREATED)
//	@JsonView(JsonViews.BatimentWithCout.class)
//	public Defense createBatimentDefense(@Valid @RequestBody Defense defense, BindingResult br, @RequestBody List<CoutBatimentDto> list) {
//		if (br.hasErrors()) {
//			System.out.println("ici");
//			throw new BatimentException();
//		}
//		dRepo.save(defense);
//		list.stream().forEach(cbd -> {
//			CoutBatiment cb = new CoutBatiment(new CoutBatimentKey(defense,rRepo.findById(cbd.getIdRessource()).get()),cbd.getCout());
//			cbRepo.save(cb);
//		});
//		return defense;
//	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	@JsonView(JsonViews.BatimentWithCout.class)
	public Defense createBatimentDefense(@Valid @RequestBody Defense defense, BindingResult br) {
		if (br.hasErrors()) {
			throw new BatimentException();
		}
		dRepo.save(defense);
		
		List<CoutBatiment> list = defense.getCoutBatiment();
		if(!(list == null)) {
		list.stream().forEach(cbd -> {
			CoutBatiment cb = new CoutBatiment(new CoutBatimentKey(defense,cbd.getId().getRessource()),cbd.getCout());
			cbRepo.save(cb);
		});
		}
		return defense;
	}
	
	
//	@PutMapping("/{id}")
//	@JsonView(JsonViews.BatimentWithCout.class)
//	public Defense replaceBatimentDefense(@Valid @RequestBody Defense defense, BindingResult br, @RequestBody List<CoutBatimentDto> list, @PathVariable Integer id) {
//		if(br.hasErrors()) {
//			throw new BatimentException();
//		}
//		Optional<Defense> opt = dRepo.findById(id);
//		if(opt.isPresent()) {
//			defense.setId(id);
//			for(CoutBatiment cb : opt.get().getCoutBatiment()) {
//				cbRepo.delete(cb);
//			}
//			list.stream().forEach(cbd -> {
//				CoutBatiment cb = new CoutBatiment(new CoutBatimentKey(defense,rRepo.findById(cbd.getIdRessource()).get()),cbd.getCout());
//				cbRepo.save(cb);
//			});
//			return dRepo.save(defense);
//		}
//		throw new BatimentException();
//	}
	
	@PutMapping("/{id}")
	@JsonView(JsonViews.BatimentWithCout.class)
	public Defense replaceBatimentDefense(@Valid @RequestBody Defense defense, BindingResult br, @PathVariable Integer id) {
		if(br.hasErrors()) {
			throw new BatimentException();
		}
		Optional<Defense> opt = dRepo.findById(id);
		if(opt.isPresent()) {
			defense.setId(id);			
			for(CoutBatiment cb : opt.get().getCoutBatiment()) {
				System.out.println("ici");
				cbRepo.delete(cb);
			}
			System.out.println("là");
			List<CoutBatiment> list = defense.getCoutBatiment();
			if(!(list == null)) {
			list.stream().forEach(cbd -> {
//				CoutBatiment cb = new CoutBatiment(new CoutBatimentKey(defense,rRepo.findById(cbd.getIdRessource()).get()),cbd.getCout());
				CoutBatiment cb = new CoutBatiment(new CoutBatimentKey(defense,cbd.getId().getRessource()),cbd.getCout());
				cbRepo.save(cb);
			});
			}
			defense.setCost(null);
			return dRepo.save(defense);
		}else {
			throw new BatimentException();
		}
	}
	
	@PatchMapping("/{id}")
	@JsonView(JsonViews.BatimentWithCout.class)
	public Defense modifyBatimentDefense(@RequestBody Map<String,Object> fields, @PathVariable Integer id) {
		Optional<Defense> opt = dRepo.findById(id);
		if(opt.isPresent()) {
			Defense batDefEnBase = opt.get();
			fields.forEach((key,value) -> {
				Field field = ReflectionUtils.findField(Defense.class, key);
				ReflectionUtils.makeAccessible(field);
				ReflectionUtils.setField(field, batDefEnBase, value);
			});
			return dRepo.save(batDefEnBase);
		}
		throw new BatimentException();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBatimentDefense(@PathVariable Integer id) {
		Optional<Defense> opt = dRepo.findById(id);
		if(opt.isPresent()) {
			Defense batDefADelete = opt.get();
			List<CoutBatiment> cbDefADelete = batDefADelete.getCoutBatiment();
			for(CoutBatiment cb : cbDefADelete) {
				cbRepo.delete(cb);
			}
			dRepo.delete(batDefADelete);
		}
		else {
		throw new BatimentException();
		}
	}
}
