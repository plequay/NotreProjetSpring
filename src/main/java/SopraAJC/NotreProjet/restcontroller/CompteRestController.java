package SopraAJC.NotreProjet.restcontroller;

import SopraAJC.NotreProjet.exceptions.CompteException;
import SopraAJC.NotreProjet.models.Compte;
import SopraAJC.NotreProjet.models.CompteUserDetails;
import SopraAJC.NotreProjet.models.JsonViews;
import SopraAJC.NotreProjet.repositories.CompteRepository;
import SopraAJC.NotreProjet.services.CompteService;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/compte")
@CrossOrigin(origins = "*")
public class CompteRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompteRestController.class);

    @Autowired
    CompteRepository compteRepository;

    @Autowired
    CompteService compteService;

    @GetMapping("")
    @JsonView(JsonViews.Common.class)
    public List<Compte> allCompte(){
        return compteRepository.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(JsonViews.Common.class)
    public Compte compteById(@PathVariable("id") Integer id){
        return compteService.compteExistsInDB(id);
    }

    @GetMapping("/byUsername/{username}")
    @JsonView(JsonViews.Common.class)
    public Compte compteByUsername(@PathVariable("username") String username){
        return compteService.compteIsInDatabaseByUsername(username);
    }

    @GetMapping("/isUsernameInDb/{username}")
    public Boolean isUsernameInDatabase(@PathVariable("username") String username){
        return compteService.isUsernameInDatabase(username);
    }

    @GetMapping("/authentification")
    @JsonView(JsonViews.Common.class)
    public Compte authentification(@AuthenticationPrincipal CompteUserDetails compteUserDetails){
        LOGGER.info("compteUserDetails " +  compteUserDetails.getCompte().getUsername() );
        return compteUserDetails.getCompte();
    }
}
