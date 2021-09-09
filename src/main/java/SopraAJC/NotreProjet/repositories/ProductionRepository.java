package SopraAJC.NotreProjet.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import SopraAJC.NotreProjet.models.Production;



public interface ProductionRepository extends JpaRepository <Production, Integer> {

	@Query("from Production")
	List<Production> findProductionBatiment();

}
