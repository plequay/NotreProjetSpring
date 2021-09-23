package SopraAJC.NotreProjet.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import SopraAJC.NotreProjet.dto.SessionDto;
import SopraAJC.NotreProjet.exceptions.SessionException;
import SopraAJC.NotreProjet.models.Batiment;
import SopraAJC.NotreProjet.models.JsonViews;
import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.models.SessionBatiment;
import SopraAJC.NotreProjet.models.SessionRessource;
import SopraAJC.NotreProjet.repositories.CompteRepository;
import SopraAJC.NotreProjet.repositories.PartieRepository;
import SopraAJC.NotreProjet.repositories.SessionBatimentRepository;
import SopraAJC.NotreProjet.repositories.SessionRepository;
import SopraAJC.NotreProjet.repositories.SessionRessourceRepository;
import SopraAJC.NotreProjet.services.SessionService;

@RestController
@RequestMapping("/api/session")
@CrossOrigin(origins = "*")
public class SessionRestController {

    @Autowired
    SessionService sessionService;

    @Autowired
    SessionRepository sessionRepository;
    
    @Autowired
    SessionBatimentRepository sessionBatRepo;
    
    @Autowired
    SessionRessourceRepository sessionResRepo;
    
    @Autowired
    private PartieRepository pRepo;
    
    @Autowired
    private CompteRepository cRepo;
    

    @GetMapping("/{idPartie}/{idCompte}")
    @JsonView(JsonViews.SessionWithAll.class)
    public Session getSession(@PathVariable Integer idPartie, @PathVariable Integer idCompte){
        return sessionRepository.findByPartieAndCompte(pRepo.findById(idPartie).get(),cRepo.findById(idCompte).get()).get();
    }
    
    @GetMapping("")
    @JsonView(JsonViews.SessionWithAll.class)
    public List<Session> allSession(){

        return sessionRepository.findAll();

    }
    @GetMapping("/{idP}")
    @JsonView(JsonViews.SessionWithAll.class)
    public List<Session> getSessionByPartie(@PathVariable Integer idP){
        return sessionRepository.findByPartie(pRepo.findById(idP).get());
    }
    @GetMapping("/compte/{idCompte}")
    @JsonView(JsonViews.SessionWithAll.class)
    public List<Session> getSessionByCompte(@PathVariable Integer idCompte){
        return sessionRepository.findByCompte(cRepo.findById(idCompte).get());
    }

    @PostMapping("")
    @JsonView(JsonViews.SessionWithPartieAndCompte.class)
    @ResponseStatus(HttpStatus.CREATED)
    public Session createSession(@RequestBody SessionDto sessionDto){

        Session session = sessionService.createSession(sessionDto);

        return session;
    }
    
    @PutMapping(value = "/roulement/{idPartie}/{idCompte}")
    @JsonView(JsonViews.SessionWithAll.class)
	public Session invertTourEnCours (@PathVariable Integer idPartie, @PathVariable Integer idCompte) {
    	Session session = sessionRepository.findByPartieAndCompte(pRepo.findById(idPartie).get() , cRepo.findById(idCompte).get()).get();
    	if (session.isTourEnCours()) {
    		session.setTourEnCours(false);
    		for(SessionBatiment sb:session.getSessionBatiment()){
    			sb.setUsed(false);
    		}
    	}else {
    		session.setTourEnCours(true);
    	}
    	sessionRepository.save(session);
		return session;
	}

    @PostMapping("/createSessions")
    @JsonView(JsonViews.SessionWithPartieAndCompte.class)
    @ResponseStatus(HttpStatus.CREATED)
    public List<Session> createSession(@RequestBody List<SessionDto> sessionDtoList){

        if (sessionDtoList.isEmpty()){
            throw new SessionException();
        }

        List<Session> sessions = new ArrayList<>();

        sessionDtoList.stream().forEach(sessionDto -> {
            Session session = sessionService.createSession(sessionDto);
            sessions.add(session);
        });

        return sessions;
    }
    
    @DeleteMapping("/del/{idP}/{idC}")
    public void deleteSession(@PathVariable Integer idP, @PathVariable Integer idC) {
    	Session session=sessionRepository.findByPartieAndCompte(pRepo.findById(idP).get(), cRepo.findById(idC).get()).get();
    	for (SessionBatiment sb : session.getSessionBatiment()) {
    		sessionBatRepo.delete(sb);
    	}
    	for (SessionRessource sr: session.getSessionRessource()) {
    		sessionResRepo.delete(sr);
    	}
    	sessionRepository.delete(session);
    }

    

}
