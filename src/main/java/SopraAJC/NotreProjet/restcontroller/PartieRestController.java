package SopraAJC.NotreProjet.restcontroller;

import SopraAJC.NotreProjet.dto.PartieDto;
import SopraAJC.NotreProjet.exceptions.PartieException;
import SopraAJC.NotreProjet.models.JsonViews;
import SopraAJC.NotreProjet.models.Partie;
import SopraAJC.NotreProjet.repositories.CompteRepository;
import SopraAJC.NotreProjet.repositories.PartieRepository;
import SopraAJC.NotreProjet.services.CompteService;
import SopraAJC.NotreProjet.services.ConsoleService;
import SopraAJC.NotreProjet.services.PartieService;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/partie")
@CrossOrigin(origins = "*")
public class PartieRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartieRestController.class);

    @Autowired
    PartieService partieService;

    @Autowired
    PartieRepository partieRepository;
    
    @Autowired
    CompteRepository compteRepo;

    @GetMapping("")
    @JsonView(JsonViews.Common.class)
    public List<Partie> allPartie(){
        return partieRepository.findAll();
    }
//    @GetMapping("/{id}")
//    @JsonView(JsonViews.PartieWithSession.class)
//    public List<Partie> partieByCompte(@PathVariable Integer id){
//        return partieRepository.findByCompte(compteRepo.findById(id).get());
//    }

    @GetMapping("/withSession")
    @JsonView(JsonViews.PartieWithSession.class)
    public List<Partie> allPartieWithSession(){
        return partieRepository.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(JsonViews.Common.class)
    public Partie partieById(@PathVariable("id") Integer id){
        partieService.partieExistsInDB(id);
        return partieRepository.findById(id).get();
    }

    @GetMapping("/{id}/withSession")
    @JsonView(JsonViews.PartieWithSession.class)
    public Partie partieByIdWithSession(@PathVariable("id") Integer id){
        partieService.partieExistsInDB(id);
        return partieRepository.findById(id).get();
    }

    @PostMapping("")
    @JsonView(JsonViews.PartieWithSession.class)
    public Partie createPartie(){
        return partieService.createPartie();
    }

    @PostMapping("/withDescription")
    @JsonView(JsonViews.PartieWithSession.class)
    public Partie createPartieWithDescription(@Valid @RequestBody Partie partie, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            LOGGER.info(partie.getDescription());
            throw new PartieException();
        }
        return partieService.createPartie(partie);
    }

    @PutMapping("/{id}")
    @JsonView(JsonViews.PartieWithSession.class)
    public Partie put(@Valid @RequestBody Partie partie, BindingResult bindingResult, @PathVariable Integer id){
        if(bindingResult.hasErrors()){
            throw new PartieException();
        }
        return partieService.put(id, partie);
    }

    @PatchMapping("/{id}")
    @JsonView(JsonViews.PartieWithSession.class)
    public Partie patch(@RequestBody Map<String, Object> fields, @PathVariable Integer id) {
        LOGGER.info("in PartieRestController patch");
        LOGGER.info((String) fields.get("description")); //Probleme dans angular quand on envoie une map ?
        return partieService.patch(id, fields);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        partieService.delete(id);
    }

}
