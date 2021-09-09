package SopraAJC.NotreProjet.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import SopraAJC.NotreProjet.models.Ressource;


public interface RessourceRepository  extends JpaRepository <Ressource, Integer>{

	Optional<Ressource> findByNom(String nom);
}
