package SopraAJC.NotreProjet.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import SopraAJC.NotreProjet.models.Defense;



public interface DefenseRepository extends JpaRepository <Defense, Integer> {

	@Query("from Defense")
	List<Defense> findDefenseBatiment();
}
