package SopraAJC.NotreProjet.services;

import SopraAJC.NotreProjet.dto.SessionDto;
import SopraAJC.NotreProjet.exceptions.SessionException;
import SopraAJC.NotreProjet.models.*;
import SopraAJC.NotreProjet.repositories.CompteRepository;
import SopraAJC.NotreProjet.repositories.PartieRepository;
import SopraAJC.NotreProjet.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    CompteService compteService;

    @Autowired
    PartieService partieService;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    PartieRepository partieRepository;

    @Autowired
    CompteRepository compteRepository;

    public Session createSession(SessionDto sessionDto){

        sessionDataIdIsNotNull(sessionDto);
        Partie partie = partieService.partieExistsInDB(sessionDto.getPartieId());
        Compte compte = compteService.compteExistsInDB(sessionDto.getCompteId());

        SessionKey sessionKey = new SessionKey(partie, compte);

        Optional<Session> opt = sessionRepository.findById(sessionKey);

        if (opt.isPresent()){
            throw new SessionException();
        }

        Session session = new Session(sessionKey);
        session.setaJoueLeTours(sessionDto.isaJoueLeTours());
        session.setTourEnCours(sessionDto.isTourEnCours());
        session.setaCommence(sessionDto.isaCommence());
        session = sessionRepository.save(session);
        return session;
    }


    public boolean sessionDataIdIsNotNull(SessionDto sessionDto){
        if (sessionDto.getPartieId() == null){
            throw new SessionException();
        }
        if (sessionDto.getCompteId() == null){
            throw new SessionException();
        }
        return true;
    }

    /*
	    * retrouver une session par sa partie et son compte
	    * */
    public Optional<Session> findSession(Long idp, Long idc){
		Compte compte = compteService.compteExistsInDB(idc);
		Partie partie = partieService.partieExistsInDB(idp);
		return sessionRepository.findByPartieAndCompte(partie, compte);
	}

}
