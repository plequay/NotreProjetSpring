package SopraAJC.NotreProjet.restcontroller;

import SopraAJC.NotreProjet.exceptions.CompteException;
import SopraAJC.NotreProjet.models.Compte;
import SopraAJC.NotreProjet.models.Joueur;
import SopraAJC.NotreProjet.models.JsonViews;
import SopraAJC.NotreProjet.models.Partie;
import SopraAJC.NotreProjet.repositories.CompteRepository;
import SopraAJC.NotreProjet.repositories.JoueurRepository;
import SopraAJC.NotreProjet.services.JoueurService;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/joueur")
@CrossOrigin(origins = "*")
public class JoueurRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JoueurRestController.class);

    @Autowired
    CompteRepository compteRepository;

    @Autowired
    JoueurRepository joueurRepository;

    @Autowired
    JoueurService joueurService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("")
    @JsonView(JsonViews.Common.class)
    public List<Joueur> allJoueur(){
        return joueurRepository.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(JsonViews.Common.class)
    public Joueur joueurById(@PathVariable("id") Integer id){
        return joueurService.joueurExistsInDB(id);
    }

    @GetMapping("/{id}/withPartie")
    @JsonView(JsonViews.CompteWithSessionWithPartie.class)
    public Joueur joueurByIdWithPartie(@PathVariable("id") Integer id){
        joueurService.joueurExistsInDB(id);
        return joueurRepository.findById(id).get();
    }

    @PostMapping("/inscription")
    public void createJoueur(@Valid @RequestBody Joueur joueur, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new CompteException();
        }

        LOGGER.info("joueur: " + joueur.getPrenom());
        joueur.setPassword(passwordEncoder.encode(joueur.getPassword()));

        compteRepository.save(joueur);
    }
}
