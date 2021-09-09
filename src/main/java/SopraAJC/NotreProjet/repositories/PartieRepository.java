package SopraAJC.NotreProjet.repositories;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import SopraAJC.NotreProjet.models.Partie;
import SopraAJC.NotreProjet.models.Session;


public interface PartieRepository extends JpaRepository <Partie, Integer> {

}
