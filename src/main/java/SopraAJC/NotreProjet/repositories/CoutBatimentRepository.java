package SopraAJC.NotreProjet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import SopraAJC.NotreProjet.models.CoutBatiment;
import SopraAJC.NotreProjet.models.CoutBatimentKey;



public interface CoutBatimentRepository extends JpaRepository <CoutBatiment, CoutBatimentKey>{
	
//	List<CoutBatiment> findByBatiment(Batiment batiment);
//  Remplace par l'example ci-dessous + voir batimentRepository
//	CoutBatimentKey coutBatimentKey = new CoutBatimentKey(batiment, ressource)
//	findById(coutBatimentKey);
	
//	Optional<CoutBatiment> findByBatimentAndRessource(Batiment batiment, Ressource ressource);
	
	

}
