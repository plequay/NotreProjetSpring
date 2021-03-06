package SopraAJC.NotreProjet.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import SopraAJC.NotreProjet.models.Transformation;



public interface TransformationRepository extends JpaRepository <Transformation, Integer> {

	@Query("from Transformation")
	List<Transformation> findTransformationBatiment();
	
}
