package SopraAJC.NotreProjet.restcontroller;

import SopraAJC.NotreProjet.exceptions.CompteException;
import SopraAJC.NotreProjet.models.Compte;
import SopraAJC.NotreProjet.models.Joueur;
import SopraAJC.NotreProjet.repositories.CompteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/joueur")
@CrossOrigin(origins = "*")
public class JoueurRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JoueurRestController.class);

    @Autowired
    CompteRepository compteRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

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
