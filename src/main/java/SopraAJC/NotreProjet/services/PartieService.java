package SopraAJC.NotreProjet.services;

import SopraAJC.NotreProjet.dto.PartieDto;
import SopraAJC.NotreProjet.exceptions.PartieException;
import SopraAJC.NotreProjet.models.Partie;
import SopraAJC.NotreProjet.models.Session;
import SopraAJC.NotreProjet.repositories.PartieRepository;
import SopraAJC.NotreProjet.repositories.SessionRepository;
import SopraAJC.NotreProjet.restcontroller.PartieRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Service
public class PartieService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartieService.class);

    @Autowired
    PartieRepository partieRepository;

    @Autowired
    SessionRepository sessionRepository;

    public Partie createPartie(){
        return partieRepository.save(new Partie());
    }

    public Partie createPartie(Partie partie){
        if (partie.getId()!=null){
            LOGGER.info("getId");
            throw new PartieException();
        }
        if (!partie.getSessions().isEmpty()){
            LOGGER.info("getSession");
            throw  new PartieException();
        }
        return partieRepository.save(partie);
    }

    public Partie partieExistsInDB(Integer long1){
        Optional<Partie> opt = partieRepository.findById(long1);
        if (opt.isPresent()){
            return opt.get();
        }
        throw new PartieException();
    }

    public Partie put(Integer id, Partie partie) {
        Partie partieInDb = partieExistsInDB(id);
        if (partie.getId()!=null){
            partie.setId(partieInDb.getId());
        }
        return partieRepository.save(partie);
    }


    public Partie patch(Integer id, Map<String, Object> fields) {
        Partie partie = partieExistsInDB(id);
        fields.forEach((key,value)-> {
            Field field = ReflectionUtils.findField(Partie.class, key);
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, partie, value);
        });
        return  partieRepository.save(partie);
    }

    public void delete(Integer id) {
        Partie partie = partieExistsInDB(id);
        if(partie.getSessions()!=null) {
            for (Session session : partie.getSessions()
            ) {
                sessionRepository.delete(session);
            }
        }
        partieRepository.delete(partie);
    }
}
