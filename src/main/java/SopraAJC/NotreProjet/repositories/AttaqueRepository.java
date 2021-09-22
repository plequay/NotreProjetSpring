package SopraAJC.NotreProjet.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import SopraAJC.NotreProjet.models.Attaque;
import SopraAJC.NotreProjet.models.Batiment;



public interface AttaqueRepository extends JpaRepository <Attaque, Integer> {

	@Query("from Attaque")
	List<Attaque> findAttaqueBatiment();
	
	Optional<Attaque> findByNom(String nom);
}
