package SopraAJC.NotreProjet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import SopraAJC.NotreProjet.models.Batiment;
import SopraAJC.NotreProjet.models.TransformationRessource;

public interface TransformationRessourceRepository extends JpaRepository <TransformationRessource, Integer> {

	List<TransformationRessource> findByBatiment(Batiment batiment);
}
