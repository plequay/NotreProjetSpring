package SopraAJC.NotreProjet.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import SopraAJC.NotreProjet.models.Defense;



public interface DefenseRepository extends JpaRepository <Defense, Integer> {

	@Query("from Defense")
	List<Defense> findDefenseBatiment();
	
	@Query("select d from Defense d left join fetch d.coutBatiment where d.id=:id")
	Optional<Defense> findByIdWithCoutBatiment(@Param(value = "id") Integer id);
}
