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
import SopraAJC.NotreProjet.models.Transformation;
import SopraAJC.NotreProjet.models.TransformationRessource;
import SopraAJC.NotreProjet.repositories.CoutBatimentRepository;
import SopraAJC.NotreProjet.repositories.RessourceRepository;
import SopraAJC.NotreProjet.repositories.TransformationRepository;
import SopraAJC.NotreProjet.repositories.TransformationRessourceRepository;

@RestController
@RequestMapping("/api/transformation")
@CrossOrigin(origins = "*")
public class TransformationRestController {

	@Autowired
	private TransformationRepository tRepo;
	
	@Autowired
	private TransformationRessourceRepository trRepo;
	
	@Autowired
	private CoutBatimentRepository cbRepo;
	
	@Autowired
	private RessourceRepository rRepo;
	
	@GetMapping("")
	@JsonView(JsonViews.BatimentWithCout.class)
	public List<Transformation> getAllBatimentTransformation() {
		return tRepo.findAll();
	}
	
	@GetMapping("/{id}")
	@JsonView(JsonViews.BatimentWithCoutAndListeTransformationRessource.class)
	public Transformation findBatimentTransformationById(@PathVariable Integer id) {
		Optional<Transformation> opt = tRepo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new BatimentException();
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	@JsonView(JsonViews.BatimentWithCout.class)
	public Transformation createBatimentTransformation(@Valid @RequestBody Transformation transformation, BindingResult br) {
		if (br.hasErrors()) {
			throw new BatimentException();
		}
		tRepo.save(transformation);
		List<CoutBatiment> list = transformation.getCoutBatiment();
		if(!(list == null)) {
		list.stream().forEach(cbd -> {
			CoutBatiment cb = new CoutBatiment(new CoutBatimentKey(transformation,cbd.getId().getRessource()),cbd.getCout());
			cbRepo.save(cb);
		});
		}
		List<TransformationRessource> listT = transformation.getTransformationRessouce();
		if(!(listT == null)) {
			listT.stream().forEach(e -> {
				TransformationRessource tr = new TransformationRessource(transformation,e.getRessourceLost(),e.getRessourceWin());
				trRepo.save(tr);
		});
		}
		return transformation;
	}
	
	@PutMapping("/{id}")
	@JsonView(JsonViews.BatimentWithCout.class)
	public Transformation replaceBatimentTransformation(@Valid @RequestBody Transformation transformation, BindingResult br, @PathVariable Integer id) {
//		transformation.getTransformationRessouce().stream().forEach(e-> {
//			System.out.println(e.getRessourceLost().getNom());
//			System.out.println(e.getRessourceWin().getNom());
//		});
		
		if(br.hasErrors()) {
			throw new BatimentException();
		}
		Optional<Transformation> opt = tRepo.findById(id);
		if(opt.isPresent()) {
			transformation.setId(id);
			if(!transformation.getCoutBatiment().isEmpty()) {
			for(CoutBatiment cb : opt.get().getCoutBatiment()) {
				cbRepo.delete(cb);
			}
			}
			List<CoutBatiment> list = transformation.getCoutBatiment();
			if(!(list == null)) {
			list.stream().forEach(cbd -> {
				CoutBatiment cb = new CoutBatiment(new CoutBatimentKey(transformation,cbd.getId().getRessource()),cbd.getCout());
				cbRepo.save(cb);
			});
			}
//			if(!transformation.getTransformationRessouce().isEmpty()) {
//				for(TransformationRessource tr : opt.get().getTransformationRessouce()) {
//					trRepo.delete(tr);
//			}
			List<TransformationRessource> listT = transformation.getTransformationRessouce();
			if(!(listT == null)) {
				listT.stream().forEach(e -> {
					TransformationRessource tr = new TransformationRessource(transformation,e.getRessourceLost(),e.getRessourceWin());
					trRepo.save(tr);
			});
			}
			
			transformation.setCost(null);
			return tRepo.save(transformation);
		}
		throw new BatimentException();
	}
	
	@PatchMapping("/{id}")
	@JsonView(JsonViews.BatimentWithCout.class)
	public Transformation modifyBatimentTransformation(@RequestBody Map<String,Object> fields, @PathVariable Integer id) {
		Optional<Transformation> opt = tRepo.findById(id);
		if(opt.isPresent()) {
			Transformation batTransEnBase = opt.get();
			fields.forEach((key,value) -> {
				Field field = ReflectionUtils.findField(Transformation.class, key);
				ReflectionUtils.makeAccessible(field);
				ReflectionUtils.setField(field, batTransEnBase, value);
			});
			return tRepo.save(batTransEnBase);
		}
		throw new BatimentException();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBatimentTransformation(@PathVariable Integer id) {
		Optional<Transformation> opt = tRepo.findById(id);
		if(opt.isPresent()) {
			Transformation batTransADelete = opt.get();
			List<CoutBatiment> cbTransADelete = batTransADelete.getCoutBatiment();
			for(CoutBatiment cb : cbTransADelete) {
				cbRepo.delete(cb);
			}
			tRepo.delete(batTransADelete);
		}else {
		throw new BatimentException();
		}
	}
}
