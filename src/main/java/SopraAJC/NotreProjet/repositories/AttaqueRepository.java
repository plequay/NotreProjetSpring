package SopraAJC.NotreProjet.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import SopraAJC.NotreProjet.models.Attaque;



public interface AttaqueRepository extends JpaRepository <Attaque, Integer> {

	@Query("from Attaque")
	List<Attaque> findAttaqueBatiment();
}
