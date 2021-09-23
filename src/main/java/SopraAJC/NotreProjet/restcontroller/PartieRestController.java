package SopraAJC.NotreProjet.restcontroller;

import SopraAJC.NotreProjet.dto.PartieDto;
import SopraAJC.NotreProjet.exceptions.PartieException;
import SopraAJC.NotreProjet.models.Joueur;
import SopraAJC.NotreProjet.models.JsonViews;
import SopraAJC.NotreProjet.models.Partie;
import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.models.SessionBatiment;
import SopraAJC.NotreProjet.models.SessionRessource;
import SopraAJC.NotreProjet.repositories.CompteRepository;
import SopraAJC.NotreProjet.repositories.JoueurRepository;
import SopraAJC.NotreProjet.repositories.PartieRepository;
import SopraAJC.NotreProjet.repositories.SessionBatimentRepository;
import SopraAJC.NotreProjet.repositories.SessionRepository;
import SopraAJC.NotreProjet.repositories.SessionRessourceRepository;
import SopraAJC.NotreProjet.services.CompteService;
import SopraAJC.NotreProjet.services.ConsoleService;
import SopraAJC.NotreProjet.services.JoueurService;
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
    @Autowired
    SessionBatimentRepository sessionBatRepo;
    
    @Autowired
    SessionRessourceRepository sessionResRepo;
    @Autowired
    SessionRepository sessionRepo;

    @Autowired
    JoueurRepository joueurRepository;

    @Autowired
    JoueurService joueurService;

    @GetMapping("")
    @JsonView(JsonViews.Common.class)
    public List<Partie> allPartie(){
        return partieRepository.findAll();
    }

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

//    @GetMapping("/{id}/joueur")
//    @JsonView(JsonViews.Common.class)
//    public List<Partie> partieByIdJoueur(@PathVariable("id") Integer joueurId){
//        Joueur joueur = joueurService.joueurExistsInDB(joueurId);
//        partieRepository.findByJoueur(joueur);
//    }


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
    	Partie partie = partieRepository.findById(id).get();
    	for (Session session: partie.getSessions()) {
    	for (SessionBatiment sb : session.getSessionBatiment()) {
    		sessionBatRepo.delete(sb);
    	}
    	for (SessionRessource sr: session.getSessionRessource()) {
    		sessionResRepo.delete(sr);
    	}
    	sessionRepo.delete(session);
    	}
        partieService.delete(id);
    }

}
