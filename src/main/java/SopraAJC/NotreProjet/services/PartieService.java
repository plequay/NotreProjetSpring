package SopraAJC.NotreProjet.services;

import SopraAJC.NotreProjet.dto.PartieDto;
import SopraAJC.NotreProjet.exceptions.PartieException;
import SopraAJC.NotreProjet.models.Partie;
import SopraAJC.NotreProjet.repositories.PartieRepository;
import SopraAJC.NotreProjet.restcontroller.PartieRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PartieService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PartieService.class);

    @Autowired
    PartieRepository partieRepository;

    public Partie createPartie(){
        return partieRepository.save(new Partie());
    }

    public Partie partieExistsInDB(Long id){
        Optional<Partie> opt = partieRepository.findById(id);
        if (opt.isPresent()){
            return opt.get();
        }
        throw new PartieException();
    }


}
