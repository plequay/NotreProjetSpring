package SopraAJC.NotreProjet.restcontroller;

import SopraAJC.NotreProjet.dto.PartieDto;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @JsonView(JsonViews.PartieWithSession.class)
    public List<Partie> allPartie(){
        return partieRepository.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(JsonViews.PartieWithSession.class)
    public Partie partieById(@PathVariable("id") Integer id){
        partieService.partieExistsInDB(id);
        return partieRepository.findById(id).get();
    }

    @PostMapping("")
    @JsonView(JsonViews.PartieWithSession.class)
    public Partie createPartie(){
        return partieService.createPartie();
    }

}
