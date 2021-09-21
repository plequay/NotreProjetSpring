package SopraAJC.NotreProjet.restcontroller;

import SopraAJC.NotreProjet.exceptions.SessionException;
import SopraAJC.NotreProjet.models.JsonViews;
import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.dto.SessionDto;
import SopraAJC.NotreProjet.repositories.SessionRepository;
import SopraAJC.NotreProjet.services.SessionService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/session")
@CrossOrigin(origins = "*")
public class SessionRestController {

    @Autowired
    SessionService sessionService;

    @Autowired
    SessionRepository sessionRepository;
    

    @GetMapping("")
    @JsonView(JsonViews.SessionWithAll.class)
    public List<Session> allSession(){

        return sessionRepository.findAll();

    }
    
    @GetMapping("/{idPartie}/{idCompte}")
    @JsonView(JsonViews.SessionWithPartieAndCompte.class)
    public Session findByCompteAndPartie(@PathVariable Integer idPartie, @PathVariable Integer idCompte) {
    	return sessionService.findSession(idPartie, idCompte).get();
    }

    @PostMapping("")
    @JsonView(JsonViews.SessionWithPartieAndCompte.class)
    @ResponseStatus(HttpStatus.CREATED)
    public Session createSession(@RequestBody SessionDto sessionDto){

        Session session = sessionService.createSession(sessionDto);

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


}
