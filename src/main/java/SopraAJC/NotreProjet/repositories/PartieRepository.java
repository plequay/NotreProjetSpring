package SopraAJC.NotreProjet.repositories;



import java.util.List;
import java.util.Optional;

import SopraAJC.NotreProjet.models.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;

import SopraAJC.NotreProjet.models.Compte;
import SopraAJC.NotreProjet.models.Partie;
import SopraAJC.NotreProjet.models.Session;


public interface PartieRepository extends JpaRepository <Partie, Integer> {


    List<Partie> findByJoueur(Joueur joueur);
}
