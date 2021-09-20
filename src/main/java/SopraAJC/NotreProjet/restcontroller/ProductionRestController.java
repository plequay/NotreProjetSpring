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
import SopraAJC.NotreProjet.models.JsonViews;
import SopraAJC.NotreProjet.models.Production;
import SopraAJC.NotreProjet.repositories.CoutBatimentRepository;
import SopraAJC.NotreProjet.repositories.ProductionRepository;
import SopraAJC.NotreProjet.repositories.RessourceRepository;

@RestController
@RequestMapping("/api/production")
@CrossOrigin(origins = "*")
public class ProductionRestController {

	@Autowired
	private ProductionRepository pRepo;
	
	@Autowired
	private CoutBatimentRepository cbRepo;
	
	@Autowired
	private RessourceRepository rRepo;
	
	@GetMapping("")
	@JsonView(JsonViews.BatimentWithCout.class)
	public List<Production> getAllBatimentProduction() {
		return pRepo.findAll();
	}
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.BatimentWithCout.class)
	public Production findBatimentProductionById(@PathVariable Integer id) {
		Optional<Production> opt = pRepo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new BatimentException();
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	@JsonView(JsonViews.BatimentWithCout.class)
	public Production createBatimentProduction(@Valid @RequestBody Production production, BindingResult br, @RequestBody List<CoutBatimentDto> list) {
		if (br.hasErrors()) {
			throw new BatimentException();
		}
		pRepo.save(production);
		list.stream().forEach(cbd -> {
			CoutBatiment cb = new CoutBatiment(new CoutBatimentKey(production,rRepo.findById(cbd.getIdRessource()).get()),cbd.getCout());
			cbRepo.save(cb);
		});
		return production;
	}
	
	@PutMapping("/{id}")
	@JsonView(JsonViews.BatimentWithCout.class)
	public Production replaceBatimentProduction(@Valid @RequestBody Production production, BindingResult br, @RequestBody List<CoutBatimentDto> list, @PathVariable Integer id) {
		if(br.hasErrors()) {
			throw new BatimentException();
		}
		Optional<Production> opt = pRepo.findById(id);
		if(opt.isPresent()) {
			production.setId(id);
			for(CoutBatiment cb : opt.get().getCoutBatiment()) {
				cbRepo.delete(cb);
			}
			list.stream().forEach(cbd -> {
				CoutBatiment cb = new CoutBatiment(new CoutBatimentKey(production,rRepo.findById(cbd.getIdRessource()).get()),cbd.getCout());
				cbRepo.save(cb);
			});
			return pRepo.save(production);
		}
		throw new BatimentException();
	}
	
	@PatchMapping("/{id}")
	@JsonView(JsonViews.BatimentWithCout.class)
	public Production modifyBatimentProduction(@RequestBody Map<String,Object> fields, @PathVariable Integer id) {
		Optional<Production> opt = pRepo.findById(id);
		if(opt.isPresent()) {
			Production batProdEnBase = opt.get();
			fields.forEach((key,value) -> {
				Field field = ReflectionUtils.findField(Production.class, key);
				ReflectionUtils.makeAccessible(field);
				ReflectionUtils.setField(field, batProdEnBase, value);
			});
			return pRepo.save(batProdEnBase);
		}
		throw new BatimentException();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBatimentProduction(@PathVariable Integer id) {
		Optional<Production> opt = pRepo.findById(id);
		if(opt.isPresent()) {
			Production batProdADelete = opt.get();
			List<CoutBatiment> cbProdADelete = batProdADelete.getCoutBatiment();
			for(CoutBatiment cb : cbProdADelete) {
				cbRepo.delete(cb);
			}
			pRepo.delete(batProdADelete);
		}
		throw new BatimentException();
	}
}
