package SopraAJC.NotreProjet.restcontroller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
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
import SopraAJC.NotreProjet.models.Attaque;
import SopraAJC.NotreProjet.models.Batiment;
import SopraAJC.NotreProjet.models.CoutBatiment;
import SopraAJC.NotreProjet.models.CoutBatimentKey;
import SopraAJC.NotreProjet.models.Defense;
import SopraAJC.NotreProjet.models.CoutBatimentDto;
import SopraAJC.NotreProjet.models.JsonViews;
import SopraAJC.NotreProjet.models.Production;
import SopraAJC.NotreProjet.models.Transformation;
import SopraAJC.NotreProjet.repositories.AttaqueRepository;
import SopraAJC.NotreProjet.repositories.BatimentRepository;
import SopraAJC.NotreProjet.repositories.CoutBatimentRepository;
import SopraAJC.NotreProjet.repositories.DefenseRepository;
import SopraAJC.NotreProjet.repositories.ProductionRepository;
import SopraAJC.NotreProjet.repositories.RessourceRepository;
import SopraAJC.NotreProjet.repositories.TransformationRepository;

@RestController
@RequestMapping("/api/batiment")
@CrossOrigin(origins = "*")
public class BatimentRestController {

	@Autowired
	private BatimentRepository bRepo;
	
	@Autowired
	private CoutBatimentRepository cbRepo;
	
	@Autowired
	private RessourceRepository rRepo;
	
	@Autowired
	private AttaqueRepository attaqueRepo;
	
	@Autowired
	private DefenseRepository defenseRepo;
	
	@Autowired
	private ProductionRepository productionRepo;
	
	@Autowired
	private TransformationRepository transformationRepo;
	
	@GetMapping("")
	@JsonView(JsonViews.BatimentWithCout.class)
	public List<Batiment> getAll() {
		return bRepo.findAll();
	}
	
	@GetMapping("/attaque")
	@JsonView(JsonViews.BatimentWithCout.class)
	public List<Attaque> getAllAttaque() {
		return attaqueRepo.findAttaqueBatiment();
	}
	
	@GetMapping("/defense")
	@JsonView(JsonViews.BatimentWithCout.class)
	public List<Defense> getAllDefense() {
		return defenseRepo.findDefenseBatiment();
	}
	
	@GetMapping("/production")
	@JsonView(JsonViews.BatimentWithCoutAndRessourceProduite.class)
	public List<Production> getAllProduction() {
		return productionRepo.findProductionBatiment();
	}
	
	@GetMapping("/transformation")
	@JsonView(JsonViews.BatimentWithCoutAndListeTransformationRessource.class)
	public List<Transformation> getAllTransformation() {
		return transformationRepo.findTransformationBatiment();
	}
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.BatimentWithCout.class)
	public Batiment findById(@PathVariable Integer id) {
		Optional<Batiment> opt = bRepo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new BatimentException();
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	@JsonView(JsonViews.BatimentWithCout.class)
	public Batiment add(@Valid @RequestBody Batiment batiment, BindingResult br, @RequestBody List<CoutBatimentDto> list) {
		if (br.hasErrors()) {
			throw new BatimentException();
		}
		bRepo.save(batiment);
		list.stream().forEach(cbd -> {
			CoutBatiment cb = new CoutBatiment(new CoutBatimentKey(batiment,rRepo.findById(cbd.getIdRessource()).get()),cbd.getCout());
			cbRepo.save(cb);
		});
		return batiment;
	}
	
	@PutMapping("/{id}")
	@JsonView(JsonViews.BatimentWithCout.class)
	public Batiment replace(@Valid @RequestBody Batiment batiment, BindingResult br, @RequestBody List<CoutBatimentDto> list, @PathVariable Integer id) {
		if(br.hasErrors()) {
			throw new BatimentException();
		}
		Optional<Batiment> opt = bRepo.findById(id);
		if(opt.isPresent()) {
			batiment.setId(id);
			for(CoutBatiment cb : opt.get().getCoutBatiment()) {
				cbRepo.delete(cb);
			}
			list.stream().forEach(cbd -> {
				CoutBatiment cb = new CoutBatiment(new CoutBatimentKey(batiment,rRepo.findById(cbd.getIdRessource()).get()),cbd.getCout());
				cbRepo.save(cb);
			});
			return bRepo.save(batiment);
		}
		throw new BatimentException();
	}
	
	@PatchMapping("/{id}")
	@JsonView(JsonViews.BatimentWithCout.class)
	public Batiment modify(@RequestBody Map<String,Object> fields, @PathVariable Integer id) {
		Optional<Batiment> opt = bRepo.findById(id);
		if(opt.isPresent()) {
			Batiment batEnBase = opt.get();
			fields.forEach((key,value) -> {
				Field field = ReflectionUtils.findField(Batiment.class, key);
				ReflectionUtils.makeAccessible(field);
				ReflectionUtils.setField(field, batEnBase, value);
			});
			return bRepo.save(batEnBase);
		}
		throw new BatimentException();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Batiment delete(@PathVariable Integer id) {
		Optional<Batiment> opt = bRepo.findById(id);
		if(opt.isPresent()) {
			Batiment batADelete = opt.get();
			List<CoutBatiment> cbADelete = batADelete.getCoutBatiment();
			for(CoutBatiment cb : cbADelete) {
				cbRepo.delete(cb);
			}
			bRepo.delete(batADelete);
		}
		throw new BatimentException();
	}
	
}
