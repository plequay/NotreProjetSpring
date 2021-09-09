package SopraAJC.NotreProjet.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import SopraAJC.NotreProjet.models.Batiment;

public interface BatimentRepository extends JpaRepository <Batiment, Integer> {
		
	List<Batiment> findByAmeliorable(Boolean ameliorable);
	
	Optional<Batiment> findByNom(String nom);
	
	@Query("select b from Batiment b left join fetch b.coutBatiment where b.id=:id")
	Optional<Batiment> findByIdWithCoutBatiment(@Param(value = "id") Integer id);

}
