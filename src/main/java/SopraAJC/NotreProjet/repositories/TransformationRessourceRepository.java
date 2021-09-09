package SopraAJC.NotreProjet.repositories;

import java.util.List;

import SopraAJC.NotreProjet.models.Transformation;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<<

=======
import SopraAJC.NotreProjet.models.Transformation;
>>>>>>>
import SopraAJC.NotreProjet.models.TransformationRessource;

public interface TransformationRessourceRepository extends JpaRepository <TransformationRessource, Integer> {


	List<TransformationRessource> findByTransformation(Transformation transformation);

}
