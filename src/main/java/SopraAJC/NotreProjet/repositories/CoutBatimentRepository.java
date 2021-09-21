package SopraAJC.NotreProjet.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import SopraAJC.NotreProjet.models.Batiment;
import SopraAJC.NotreProjet.models.CoutBatiment;
import SopraAJC.NotreProjet.models.CoutBatimentKey;
import SopraAJC.NotreProjet.models.Ressource;



public interface CoutBatimentRepository extends JpaRepository <CoutBatiment, CoutBatimentKey>{
	
//	List<CoutBatiment> findByBatiment(Batiment batiment);
//  Remplace par l'example ci-dessous + voir batimentRepository
//	CoutBatimentKey coutBatimentKey = new CoutBatimentKey(batiment, ressource)
//	findById(coutBatimentKey);
	
//	Optional<CoutBatiment> findByBatimentAndRessource(Batiment batiment, Ressource ressource);
	
	@Query("select cb from CoutBatiment cb where cb.id.batiment=:batiment and  cb.id.ressource in(select r from Ressource r)")
	List<CoutBatiment> findAllCoutByBatiment(@Param(value="batiment") Batiment batiment);
	
	@Query("select cb from CoutBatiment cb where cb.id.ressource=:ressource")
	List<CoutBatiment> findAllCoutByRessource(@Param(value="ressource") Ressource ressource);
}
