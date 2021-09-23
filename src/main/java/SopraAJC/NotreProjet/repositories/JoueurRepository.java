package SopraAJC.NotreProjet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import SopraAJC.NotreProjet.models.Joueur;

import java.util.Optional;

public interface JoueurRepository extends JpaRepository <Joueur, Integer> {

    Optional<Joueur> findByUsername(String username);
}
